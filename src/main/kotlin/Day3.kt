import java.io.File

class Day3(private val input: String) : Solvable {
    override fun solve() {
        val lines = File(input).readLines()

        var res = lines
            .map { i -> i.chunked(i.length / 2) }
            .map { (a, b) ->
                var res = ' '
                for (c in a.chars()) {
                    if (b.contains(c.toChar(), false)) {
                        res = c.toChar()
                    }
                }
                res
            }
            .sumOf { c ->
                if (c >= 'a') {
                    c - 'a' + 1
                } else {
                    c - 'A' + 27
                }
            }

        println(res)

        res = lines.chunked(3)
            .map {
                val (a, b, c) = it

                var item = ' '

                for (ch in CharRange('a', 'z')) {
                    if (a.contains(ch, false) && b.contains(ch, false) && c.contains(ch, false)) {
                        item = ch
                    }
                }

                for (ch in CharRange('A', 'Z')) {
                    if (a.contains(ch, false) && b.contains(ch, false) && c.contains(ch, false)) {
                        item = ch
                    }
                }

                item
            }
            .sumOf { c ->
                if (c >= 'a') {
                    c - 'a' + 1
                } else {
                    c - 'A' + 27
                }
            }

        println(res)
    }
}