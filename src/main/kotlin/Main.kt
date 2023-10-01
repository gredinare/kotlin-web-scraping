import parse.mobyGames.MobyGames


fun main(args: Array<String>) {
    val mobyGames = MobyGames()
    val test = mobyGames.parseGamePage(1)
    println(test)
}