import java.io.File

class Day1(private val input: String) {
    fun solve() {
        val file = File(this.input)

        val elves = mutableListOf(0)

        val lines = file.readLines()
        var index = 0

        for (line in lines) {
            when (val num = line.toIntOrNull()) {
                null -> {
                    index++
                    elves.add(0)
                }

                else -> {
                    elves[index] += num
                }
            }
        }

        println(elves.max())
        println(elves.sorted().takeLast(3).sum())
    }
}
