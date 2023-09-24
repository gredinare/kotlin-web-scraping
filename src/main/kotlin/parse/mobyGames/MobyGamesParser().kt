package parse.mobyGames

import parse.Parser

abstract class MobyGamesParser(): Parser() {
    protected val websiteUrl = "https://www.mobygames.com"
    protected val outputFile = "src/main/kotlin/data/output/mobyGames.xls"
}