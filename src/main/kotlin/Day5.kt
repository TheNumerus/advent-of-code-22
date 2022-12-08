import java.io.File

class Day5(private val input: String) : Solvable {
    override fun solve() {
        val (start, actions) = File(input).readText().split("\n\n")

        val actionsParsed = parseActions(actions)
        var stacks = parseStacks(start)

        for ((amount, from, to) in actionsParsed) {
            for (x in 0 until amount) {
                val item = stacks[from - 1].removeLast()
                stacks[to - 1].add(item)
            }
        }

        println(stacks.map { it.last() }.toString())

        stacks = parseStacks(start)

        for ((amount, from, to) in actionsParsed) {
            val items = stacks[from - 1].takeLast(amount)
            for (x in 0 until amount) {
                stacks[from - 1].removeLast()
            }
            stacks[to - 1].addAll(items)
        }

        println(stacks.map { it.last() }.toString())
    }

    private fun parseStacks(start: String): List<ArrayDeque<Char>> {
        val indices = mapOf(1 to 0, 5 to 1, 9 to 2, 13 to 3, 17 to 4, 21 to 5, 25 to 6, 29 to 7, 33 to 8)

        val stacks = listOf(
            ArrayDeque<Char>(),
            ArrayDeque<Char>(),
            ArrayDeque<Char>(),
            ArrayDeque<Char>(),
            ArrayDeque<Char>(),
            ArrayDeque<Char>(),
            ArrayDeque<Char>(),
            ArrayDeque<Char>(),
            ArrayDeque<Char>(),
        )

        val lines = start.lines().reversed().drop(1)

        for (line in lines) {
            for ((n, i) in indices.entries) {
                val c = line[n]
                if (c != ' ') {
                    stacks[i].add(c)
                }
            }
        }

        return stacks
    }

    private fun parseActions(actions: String): List<Triple<Int, Int, Int>> {
        return actions.trim().lines().map {
            val slices = it.split(' ')
            Triple(slices[1].toInt(), slices[3].toInt(), slices[5].toInt())
        }
    }
}