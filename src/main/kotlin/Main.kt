
import org.apache.commons.lang3.time.StopWatch
import parse.mobyGames.MobyGames

fun getAndSaveNGames(initial: Int, n: Int) {
    val stopwatch = StopWatch()
    stopwatch.start()

    val final = initial + n
    val steps = 100

    for(i in initial..final step steps) {
        println("$i - ${i + ( steps - 1)}")
        val test = MobyGames()
        test.parseGamesStepByStep(i, i + (steps - 1), 20)
    }

    stopwatch.stop()
    println(stopwatch.time)
}

fun main(args: Array<String>) {
    getAndSaveNGames(132001, 3999)
}