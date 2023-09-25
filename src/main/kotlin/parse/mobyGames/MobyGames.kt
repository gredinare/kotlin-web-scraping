package parse.mobyGames

import model.Game
import parse.Parser

class MobyGames: Parser() {
    private val websiteUrl = "https://www.mobygames.com/game/"
    private val outputFile = "src/main/kotlin/data/output/mobyGames.xls"

    companion object {
        //208838
        private var count = 0

        fun countGames(): Int {
            count += 1
            return count
        }
    }

    fun parseGames(quantity: Int) {

    }

    fun parseGamePage(gameNumber: Int): Game {
        val document = pageToDocument("$websiteUrl$gameNumber")
        val gameName = document.select(".mb-0").first()

        println("$gameNumber: ${gameName?.text()}")

        return Game(
            number = 0,
            name = "",
            release = "",
            platform = listOf(),
            mobyScore = "",
            criticScore = "",
            genre = "",
            perspective = "",
            art = "",
            setting = "",
            narrative = ""
        )
    }

}