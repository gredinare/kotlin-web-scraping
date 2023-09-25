package parse.mobyGames

import model.Game
import parse.Parser

class MobyGames: Parser() {
    private val websiteUrl = "https://www.mobygames.com/game/"
    private val outputFile = "src/main/kotlin/data/output/mobyGames.xls"

    fun parseGamePage(gameNumber: Int): Game {
        val document = pageToDocument("$websiteUrl$gameNumber")

        val gameName: String = document.select(".mb-0").first()?.text() ?: "null"

        println("$gameNumber: $gameName")

        return Game(
            number = gameNumber,
            name = gameName,
        )
    }

}