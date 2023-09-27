
import parse.mobyGames.MobyGames

fun testInfoGame() {
    val test = MobyGames()
    test.parseGamesStepByStep(1, 10, 100)
}

fun main(args: Array<String>) {
    val test = MobyGames()
    val game = test.parseGamePage(1)

    println(game)
}