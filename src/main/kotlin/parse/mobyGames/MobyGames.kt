package parse.mobyGames

import data.ManipulateXls
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import model.Game
import parse.Parser

class MobyGames: Parser() {
    private val websiteUrl = "https://www.mobygames.com/game/"
    private val outputFile = "src/main/kotlin/data/output/mobyGames.xls"
    val xlsFile = ManipulateXls(outputFile)

    fun parseGames() {
        val gameList = mutableListOf<Game>()

        runBlocking {
            coroutineScope {
                (1..20).map { i ->
                    async(Dispatchers.IO) {
                        val game = parseGamePage(i)
                        gameList.add(game)
                    }
                }.awaitAll()
            }
        }

        xlsFile.saveGames(gameList)

    }

    fun parseGamePage(gameNumber: Int): Game {
        val document = pageToDocument("$websiteUrl$gameNumber")

        val gameName: String = document.select(".mb-0").first()?.text() ?: "null"

        if(gameName == "null") return Game(
            number = gameNumber,
            name = gameName,
        )

        println("${gameNumber}: $gameName")

        return Game(
            number = gameNumber,
            name = gameName,
        )
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