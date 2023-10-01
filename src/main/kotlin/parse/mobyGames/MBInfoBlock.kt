package parse.mobyGames

import org.jsoup.nodes.Element

class MBInfoBlock(val infoBlock: Element) {
    fun getRelease(): String {
        return ""
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