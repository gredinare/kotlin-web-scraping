package parse

import it.skrape.fetcher.HttpFetcher
import it.skrape.fetcher.response
import it.skrape.fetcher.skrape
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

abstract class Parser {
    private fun parsePage(websiteUrl: String): String {
        var page = ""

        val test = skrape(HttpFetcher) {
            request { url = websiteUrl }
            response { page = responseBody }
        }

        return page
    }

    fun pageToDocument(websiteUrl: String): Document {
        val document = parsePage(websiteUrl)
        return Jsoup.parse(document)
    }

}