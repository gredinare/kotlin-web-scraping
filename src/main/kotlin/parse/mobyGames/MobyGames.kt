package parse.mobyGames

import data.ManipulateXls
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import model.Game
import org.jsoup.nodes.Element
import parse.Parser
import java.lang.Thread.sleep

class MobyGames: Parser() {
    private val websiteUrl = "https://www.mobygames.com/game/"
    private val outputFile = "src/main/kotlin/data/output/mobyGames.xls"

    fun parseGamePage(gameNumber: Int): Game {
        val document = pageToDocument("$websiteUrl$gameNumber")

        // Take infos of game
        val name: String = document.select(".mb-0").first()?.text() ?: "null"

        if(name == "null") return Game(
            number = gameNumber,
            name = name,
        )

        val infoBlock = document.select("main").first()?.select("div#infoBlock")?.first()

        val releaseInfo = infoBlock?.select("div.info-release")?.select(".metadata")?.first()
        val scoreInfo = infoBlock?.select("div.info-score")?.select(".metadata")?.first()
        val genreInfo = infoBlock?.select("div.info-genre")?.select(".metadata")?.first()

        val release = releaseInfo?.select("dd")?.first()?.select("a")?.first()?.text() ?: "null"

        val mobyScore = scoreInfo?.select("dd")?.first()?.select(".mobyscore")?.text() ?: "null"
        val criticScore = scoreInfo?.select("dd")?.get(1)?.ownText() ?: "null"

        val genre: String = ""
        val perspective: String = ""
        val art: String = ""
        val setting: String = ""
        val narrative: String = ""

        val platform: List<String> = mutableListOf()


        return Game(
            number = gameNumber,
            name = name,
            release = release,
            mobyScore = mobyScore,
            criticScore = criticScore,
            genre = genre,
            perspective = perspective,
            art = art,
            setting = setting,
            narrative = narrative,
            platform = platform
        )
    }

    private fun releaseAndPlatform(releaseInfo: Element?): List<String> {
        val consoleList: List<String> = mutableListOf()

        return consoleList
    }

    private fun parseListOfGames(start: Int, final: Int): List<Game> {
        val gameList = mutableListOf<Game>()

        runBlocking {
            coroutineScope {
                (start..final).map { i ->
                    async(Dispatchers.IO) {
                        val game = parseGamePage(i)
                        gameList.add(game)
                    }
                }.awaitAll()
            }
        }
        return gameList
    }

    private fun saveGamesInXls(gameList: List<Game>) {
        val xlsFile = ManipulateXls(outputFile)
        xlsFile.saveGames(gameList)
    }

    fun parseGamesStepByStep(init: Int, steps: Int, until: Int) {
        val scope = CoroutineScope(Dispatchers.IO)

        for(i in init..until step steps) {

            val gameList = parseListOfGames(i, i + steps - 1)

            scope.launch {
                saveGamesInXls(gameList)
            }

            sleep(20000)
        }
    }



}
// number 0
// name 1
// released 2
// console 3
// mobyScore 4
// critics score 5
// genre 6
// perspective 7
// art 8
// setting 9
// narrative 10