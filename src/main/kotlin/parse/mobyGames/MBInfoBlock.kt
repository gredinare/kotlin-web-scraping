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
        val releasePlatforms = getPlatformByReleasedDate()

        return releasePlatforms.ifEmpty {
            getReleasedPlatform()
        }
    }

    private fun getReleasedPlatform(): List<String> {
        val platform = infoRelease?.select("dd")?.first()?.select("a")?.last()?.text()
        return listOf(platform!!)
    }

    private fun getPlatformByReleasedDate(): List<String> {
        val releasePlatforms: ArrayList<String> = arrayListOf()

        val listReleasePlatform = infoRelease?.select("ul#platformLinks")?.select("li")
        listReleasePlatform?.forEach {
            releasePlatforms.add(it.select("span").select("a").text())
        }

        return releasePlatforms
    }

    fun getScore(): String {
        val score = infoScore?.select("dd")?.first()?.select(".mobyscore")?.text()
        return score ?: "-"
    }

    fun getGenre(): String {
        val genre = infoGenres?.select("dd")?.first()?.select("a")?.first()?.text()
        return genre ?: "-"
    }
}