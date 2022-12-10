import kotlin.system.measureTimeMillis

val days = mapOf(
    1 to Day1("data/input_1.txt"),
    2 to Day2("data/input_2.txt"),
    3 to Day3("data/input_3.txt"),
    4 to Day4("data/input_4.txt"),
    5 to Day5("data/input_5.txt"),
    6 to Day6("data/input_6.txt"),
    7 to Day7("data/input_7.txt"),
    8 to Day8("data/input_8.txt"),
    9 to Day9("data/input_9.txt"),
    10 to Day10("data/input_10.txt"),
)

fun main() {
    val day = days[10]

    val time = measureTimeMillis {
        day!!.solve()
    }

    println("took $time ms")
}
