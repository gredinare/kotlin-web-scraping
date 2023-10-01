import parse.mobyGames.MobyGames

fun main(args: Array<String>) {
    val test = MobyGames()
    test.parseGamesStepByStep(1, 100, 20)
}