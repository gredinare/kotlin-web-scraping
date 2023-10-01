package parse.mobyGames

import model.GameModel
import parse.Parser

class MobyGames: Parser() {
    private val websiteUrl = "https://www.mobygames.com/game/"
    private val outputFile = "src/main/kotlin/data/output/mobyGames.xlsx"

    fun parseGamePage(gameNumber: Int): GameModel {
        val document = pageToDocument("$websiteUrl$gameNumber")

        // Take name of game
        val name: String = document.select(".mb-0").first()?.text() ?: "null"

        if(name == "null") return GameModel(
            id = gameNumber,
            name = name,
        )

        val infoBlock = document.select("main").first()?.select("div#infoBlock")?.first()

        return GameModel(
            id = gameNumber,
            name = name,
            release = "animal",
            platform = listOf(),
            score = "mnesarchum",
            genre = "singulis"
        )
    }
}