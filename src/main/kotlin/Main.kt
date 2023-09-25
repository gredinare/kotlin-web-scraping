
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
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

fun main(args: Array<String>) {
}

// Jogo 0
// released 1
// console 2
// mobyScore 3
// critics score 4
// Genero 5
// perspective 6
// art 7
// setting 8
// narrative 9