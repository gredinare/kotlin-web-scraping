import org.apache.commons.lang3.time.StopWatch
import parse.mobyGames.MobyGames

fun main(args: Array<String>) {
    val stopwatch = StopWatch()
    stopwatch.start()

    val start = 9101
    val final = 10000
    val steps = 100

    for(i in start..final step steps) {
        println("$i - ${i + ( steps - 1)}")
        val test = MobyGames()
        test.parseGamesStepByStep(i, i + (steps - 1), 20)
    }

    stopwatch.stop()
    println(stopwatch.time)
}