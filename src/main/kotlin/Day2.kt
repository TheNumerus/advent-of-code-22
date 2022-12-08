import java.io.File

class Day2(private val input: String) : Solvable {
    private val part1map = listOf(
        listOf(1 + 3, 2 + 6, 3 + 0),
        listOf(1 + 0, 2 + 3, 3 + 6),
        listOf(1 + 6, 2 + 0, 3 + 3)
    )

    private val part2map = listOf(
        listOf(3 + 0, 1 + 3, 2 + 6),
        listOf(1 + 0, 2 + 3, 3 + 6),
        listOf(2 + 0, 3 + 3, 1 + 6)
    )

    override fun solve() {
        val lines = File(input).readLines()

        var res = lines.sumOf { s ->
            val (a, b) = s.split(' ')

            val x = a[0] - 'A'
            val y = b[0] - 'X'

            this.part1map[x][y]
        }

        println(res)

        res = lines.sumOf { s ->
            val (a, b) = s.split(' ')

            val x = a[0] - 'A'
            val y = b[0] - 'X'

            this.part2map[x][y]
        }

        println(res)
    }
}