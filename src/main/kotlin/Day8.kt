import java.io.File
import kotlin.streams.toList

class Day8(private val input: String) : Solvable {
    private fun Boolean.toInt() = if (this) 1 else 0

    override fun solve() {
        val heights = File(input).readLines().map {
            it.chars().toList().map { c ->
                val h = c.toChar() - '0'
                Pair(h, false)
            }.toTypedArray()
        }

        for (x in heights.indices) {
            var hTop = -1

            for (y in heights[0].indices) {
                val h = heights[x][y].first

                if (h > hTop) {
                    heights[x][y] = Pair(h, true)
                    hTop = h
                }
            }

            hTop = -1

            for (y in heights[0].indices.reversed()) {
                val h = heights[x][y].first

                if (h > hTop) {
                    heights[x][y] = Pair(h, true)
                    hTop = h
                }
            }
        }

        for (x in heights[0].indices) {
            var hTop = -1

            for (y in heights.indices) {
                val h = heights[y][x].first

                if (h > hTop) {
                    heights[y][x] = Pair(h, true)
                    hTop = h
                }
            }

            hTop = -1

            for (y in heights.indices.reversed()) {
                val h = heights[y][x].first

                if (h > hTop) {
                    heights[y][x] = Pair(h, true)
                    hTop = h
                }
            }
        }

        val total = heights.sumOf {
            val visible = it.sumOf { t ->
                t.second.toInt()
            }

            visible
        }

        println(total)

        var bestScore = 0

        for (x in heights.indices) {
            val horizontal = heights[x]
            for (y in heights[0].indices) {
                val vertical = heights.map { it[y] }

                val h = heights[x][y].first

                val left = countTrees(horizontal.slice(0 until y).reversed(), h)
                val top = countTrees(vertical.slice(0 until x).reversed(), h)
                val right = countTrees(horizontal.slice((y + 1) until horizontal.size), h)
                val down = countTrees(vertical.slice((x + 1) until vertical.size), h)

                val score = left * top * right * down

                if (score > bestScore) {
                    bestScore = score
                }
            }
        }

        println(bestScore)
    }

    private fun countTrees(list: Iterable<Pair<Int, Boolean>>, startHeight: Int): Int {
        var trees = 0

        for ((h, _) in list) {
            if (h >= startHeight) {
                trees += 1
                break
            } else {
                trees += 1
            }
        }

        return trees
    }
}