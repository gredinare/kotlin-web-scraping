package parse.mobyGames

import data.ManipulateXls
import model.Console

object MobyGamesConsoles: MobyGamesParser() {

    private fun parserConsoleList(): List<Console> {
        val document = pageToDocument("$websiteUrl/platform")
        val consoleList = mutableListOf<Console>()

        val table = document.select(".table-hover").first()
        table?.select("tbody tr")?.forEach { row ->
            val cells = row.select("td")

            val consoleName = cells[0].text()
            val consoleGames = cells[1].text()
            val consoleYears = cells[4].text()
            val consoleLink = websiteUrl + cells[0].select("a").attr("href")

            val console = Console(
                consoleName,
                consoleGames,
                consoleYears,
                consoleLink
            )
            consoleList.add(console)
        }

        return consoleList
    }

    fun parserAndSaveConsoles() {
        val consoleList = parserConsoleList()
        val xls = ManipulateXls(outputFile)
        xls.saveConsolesInXls(consoleList)
        xls.saveFile()
    }
}