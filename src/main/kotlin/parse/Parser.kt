package parse

import it.skrape.fetcher.HttpFetcher
import it.skrape.fetcher.response
import it.skrape.fetcher.skrape
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

abstract class Parser {
    private fun parsePage(websiteUrl: String): String {
        var page = "error code: 1015"

        while (page == "error code: 1015") {
            try {
                skrape(HttpFetcher) {
                    request {
                        url = websiteUrl
                        timeout = 600000
                    }
                    response { page = responseBody }
                }
            } catch (e: Exception) {
                page = "error code: 1015"
                println(e.message)
            }
        }

        return page
    }

    fun pageToDocument(websiteUrl: String): Document {
        val document = parsePage(websiteUrl)
        return Jsoup.parse(document)
    }

}