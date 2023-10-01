package parse.mobyGames

import org.jsoup.nodes.Element
import org.jsoup.select.Elements

class MBInfoBlock(val infoBlock: Element) {
    private val infoRelease = infoBlock.select(".info-release").first()?.select(".metadata")
    private val infoScore = infoBlock.select(".info-score").first()?.select(".metadata")
    private val infoGenres = infoBlock.select(".info-genres").first()?.select(".metadata")

    fun getRelease(): String {
        val release = infoRelease?.select("dd")?.first()?.select("a")?.first()?.text()
        return release ?: "-"
    }

    fun getPlatform(): List<String> {
        val releaseList = getListAndTile(infoRelease)
        val releasePlatforms: ArrayList<String> = arrayListOf()

        try {
            val tempRelease = releaseList["Releases by Date (by platform)"]
            val listReleasePlatform = tempRelease?.split(" ")

            for(i in 1..listReleasePlatform!!.size step 2) {
                releasePlatforms.add(listReleasePlatform[i].replace("(", "").replace(")", ""))
            }
        } catch (e: Exception) {
            val tempRelease = releaseList["Released"]?.split(" ")
            releasePlatforms.add(tempRelease!![2])
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

    private fun getListAndTile(metadata: Elements?): Map<String, String> {

        val dtElements = metadata?.select("dt")?.eachText()
        val ddElements = metadata?.select("dd")?.eachText()

        val hashMap = hashMapOf<String, String>()

        for(i in dtElements!!.indices) {
            hashMap.put(dtElements[i], ddElements!![i])
        }

        return hashMap
    }
}