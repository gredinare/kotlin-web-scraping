import parse.mobyGames.MobyGames

fun test(i: Int) {
    val mobyGames = MobyGames()
    val test = mobyGames.parseGamePage(i)

    println(test)
}

fun main(args: Array<String>) {
    test(1)
    println()
    test(6)
}