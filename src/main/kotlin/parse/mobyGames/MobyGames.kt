package parse.mobyGames

import parse.Parser

class MobyGames: Parser() {
    private val websiteUrl = "https://www.mobygames.com/game/"
    private val outputFile = "src/main/kotlin/data/output/mobyGames.xls"
}