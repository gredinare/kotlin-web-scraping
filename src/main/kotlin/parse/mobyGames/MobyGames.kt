package parse.mobyGames

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import model.GameModel
import parse.ManipulateXls
import parse.Parser
import java.lang.Thread.sleep

class MobyGames: Parser() {
    private val websiteUrl = "https://www.mobygames.com/game/"
    private val outputFile = "src/main/kotlin/output/mobyGames.xlsx"

    private fun parseGamePage(gameNumber: Int): GameModel {
        val document = pageToDocument("$websiteUrl$gameNumber")

        // Take name of game
        val name: String = document.select(".mb-0").first()?.text() ?: "null"

        if(name == "null") return GameModel(
            id = gameNumber,
            name = name,
        )

        val infoBlockElement = document.select("main").first()?.select("div#infoBlock")?.first()
        val infoBlock = MBInfoBlock(infoBlockElement!!)

        val release = infoBlock.getRelease()
        val platform = infoBlock.getPlatform()
        val score = infoBlock.getScore()
        val genre = infoBlock.getGenre()

        return GameModel(
            id = gameNumber,
            name = name,
            release = release,
            platform = platform,
            score = score,
            genre = genre
        )
    }

    private fun saveGamesInXls(gameModelList: List<GameModel>) {
        val xlsFile = ManipulateXls(outputFile)
        xlsFile.saveGames(gameModelList)
    }

    private fun parseListOfGames(start: Int, final: Int): List<GameModel> {
        val gameModelList = mutableListOf<GameModel>()

        runBlocking {
            (start..final).map { i ->
                async(Dispatchers.IO) {
                    val game = parseGamePage(i)
                    println(game)
                    gameModelList.add(game)
                }
            }.awaitAll()
        }

        return gameModelList
    }

    fun parseGamesStepByStep(init: Int, until: Int, steps: Int) {
        val scope = CoroutineScope(Dispatchers.IO)

        for(i in init..until step steps) {

            val gameList = parseListOfGames(i, i + steps - 1)

            scope.launch {
                saveGamesInXls(gameList)
            }

            sleep(15000)
        }
    }
}