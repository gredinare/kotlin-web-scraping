package parse.mobyGames

import org.jsoup.nodes.Element

class MBInfoBlock(val infoBlock: Element) {
    private val infoRelease = infoBlock.select(".info-release").first()?.select(".metadata")
    private val infoScore = infoBlock.select(".info-score").first()?.select(".metadata")
    private val infoGenres = infoBlock.select(".info-genres").first()?.select(".metadata")

    fun getRelease(): String {
        val release = infoRelease?.select("dd")?.first()?.select("a")?.first()?.text()
        return release ?: "-"
    }

    fun getPlatform(): List<String> {
        return listOf()
    }

    fun getScore(): String {
        return ""
    }

    fun getGenre(): String {
        return ""
    }
}