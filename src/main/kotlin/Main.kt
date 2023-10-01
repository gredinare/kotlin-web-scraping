import parse.mobyGames.MobyGames

fun main(args: Array<String>) {
    val test = MobyGames().parseGamePage(1)
    println(test)


    val test2 = MobyGames().parseGamePage(6)
    println(test2)

}