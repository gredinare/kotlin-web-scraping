package parse.mobyGames

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import parse.Parser
import parse.mobyGames.model.GameModel
import java.lang.Thread.sleep

class MobyGames: Parser() {
    private val websiteUrl = "https://www.mobygames.com/game/"
    private val outputFile = "src/main/kotlin/data/output/mobyGames.xls"

    fun parseGamePage(gameNumber: Int): GameModel {
        val document = pageToDocument("$websiteUrl$gameNumber")

        // Take infos of game
        val name: String = document.select(".mb-0").first()?.text() ?: "null"

        if(name == "null") return GameModel(
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


        return GameModel(
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


    @OptIn(DelicateCoroutinesApi::class)
    private fun parseListOfGames(start: Int, final: Int): List<GameModel> {
        val gameModelList = mutableListOf<GameModel>()

        GlobalScope.async {
            (start..final).map { i ->
                async(Dispatchers.IO) {
                    val game = parseGamePage(i)
                    gameModelList.add(game)
                }
            }.awaitAll()
        }

        return gameModelList
    }

    private fun saveGamesInXls(gameModelList: List<GameModel>) {
        val xlsFile = ManipulateXls(outputFile)
        xlsFile.saveGames(gameModelList)
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