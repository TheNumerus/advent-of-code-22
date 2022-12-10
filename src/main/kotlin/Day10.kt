import java.io.File

class Day10(private val input: String) : Solvable {
    private val targets = setOf(20, 60, 100, 140, 180, 220)

    override fun solve() {
        val lines = File(input).readLines()

        var result = 0
        var clock = 1
        var x = 1

        val onClock = {
            if (targets.contains(clock)) {
                result += x * clock
            }
            val pos = ((clock - 1) % 40 + 1)

            if (pos == x || pos == (x + 1) || pos == (x + 2)) {
                print('▉')
            } else {
                print(' ')
            }
            if (clock % 40 == 0) {
                println()
            }
        }

        for (line in lines) {
            val splits = line.split(' ')

            when (splits[0]) {
                "noop" -> {
                    onClock()
                    clock += 1
                }

                "addx" -> {
                    onClock()
                    clock += 1
                    onClock()
                    clock += 1
                    x += splits[1].toInt()
                }
            }
        }

        println(result)
    }
}