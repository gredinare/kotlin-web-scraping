import parse.mobyGames.MobyGames

fun main(args: Array<String>) {
    val mobyGames = MobyGames()
    mobyGames.parseGamesStepByStep(1, 100, 20)
}