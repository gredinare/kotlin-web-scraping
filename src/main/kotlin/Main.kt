
import data.ManipulateXls
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import model.Game
import parse.mobyGames.MobyGames

fun testParseGames() {
    MobyGames().parseGamePage(11)

    runBlocking {
        coroutineScope {
            (21..40).map { i ->
                async(Dispatchers.IO) {
                    MobyGames().parseGamePage(i)
                }
            }.awaitAll()
        }
    }
}

fun testSaveGame() {

    val gameTest = Game(
        number = 1,
        name = "TEST",
        release = "platonem",
        platform = listOf(),
        mobyScore = "saperet",
        criticScore = "aliquet",
        genre = "natum",
        perspective = "doming",
        art = "aliquid",
        setting = "pellentesque",
        narrative = "evertitur"

    )

    val test = ManipulateXls("src/main/kotlin/data/output/mobyGames.xls")
    test.saveGame(gameTest)
    test.saveFile()

}

fun main(args: Array<String>) {
    val test = MobyGames()
    test.parseGames()

}