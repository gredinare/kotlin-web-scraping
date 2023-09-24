package parse.mobyGames

import model.Console
import model.Game
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

class MobyGamesGames(private val console: Console): MobyGamesParser() {
    private var pages: Int = 0

    private fun parsePage() {
        val document = pageToDocument(console.link)
        pages = countPages(document)
    }

    private fun countPages(document: Document): Int {
        val elementWithCount = document.select("main").select("p").dropLast(1).last()

        val pagesQuantity: Int = try {
            elementWithCount.text().split(" ")[4].toInt()
        } catch (e: Exception) {
            1
        }

        return pagesQuantity
    }

    private fun parserTable(table: Element?): List<Game> {
        val gameList = mutableListOf<Game>()
        table?.select("tbody tr")?.forEach { row ->
            val cells = row.select("td")

            val gameName = cells[0].text()
            val gameGenre = cells[1].text()
            val gameRelease = cells[2].text()
            val gameScore = cells[3].text()

            val game = Game(
                gameName,
                gameGenre,
                gameRelease,
                gameScore,
                console.platform
            )
            gameList.add(game)
        }
        return gameList
    }

    fun parserAndSaveGames() {
        parsePage()
    }
}

/*
    fun nextPage(documentJsoup : Document): String {
        val nextPage = documentJsoup.select("main").select("p")[3]
        var next: String = ""

        if (nextPage.lastElementChild()?.text() == "Next") {
            next = url + nextPage.lastElementChild()!!.attr("href")
        }

        return next
    }
 */