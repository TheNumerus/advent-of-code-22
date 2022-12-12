import java.io.File

class Day12(private val input: String) : Solvable {
    override fun solve() {
        val heightmap = buildMap(File(input).readText().trim())

        val distances = mutableMapOf(
            heightmap.start to 0,
        )

        val frontier = mutableListOf(
            heightmap.start to 0
        )

        while (frontier.isNotEmpty()) {
            val (pos, dist) = frontier.removeFirst()

            for (p in heightmap.neighbours(pos)) {
                val maybeDist = distances[p]

                if (maybeDist == null || maybeDist > (dist + 1)) {
                    distances[p] = dist + 1
                }

                if (maybeDist == null) {
                    frontier.add(p to (dist + 1))
                } else {
                    if (maybeDist > (dist + 1)) {
                        frontier.add(p to (dist + 1))
                    }
                }
            }
        }

        // debug print
        /*for (y in 0 until heightmap.heights.size) {
            for (x in 0 until heightmap.heights[0].size) {
                val d = distances[Day9.Position(x, y)]

                if (d == null) {
                    print("   ")
                } else {
                    print("%3d".format(d))
                }
                print(heightmap.heights[y][x])
                print(' ')
            }
            println()
        }*/

        println(distances[heightmap.end])

        var bestSteps = 900

        for (y in 0 until heightmap.heights.size) {
            for (x in 0 until heightmap.heights[0].size) {
                val s = heightmap.heights[y][x]
                if (s != 'a') {
                    continue
                }

                val start = Day9.Position(x, y)


                val distances = mutableMapOf(
                    start to 0,
                )

                val frontier = mutableListOf(
                    start to 0
                )

                while (frontier.isNotEmpty()) {
                    val (pos, dist) = frontier.removeFirst()

                    for (p in heightmap.neighbours(pos)) {
                        val maybeDist = distances[p]

                        if (maybeDist == null || maybeDist > (dist + 1)) {
                            distances[p] = dist + 1
                        }

                        if (maybeDist == null) {
                            frontier.add(p to (dist + 1))
                        } else {
                            if (maybeDist > (dist + 1)) {
                                frontier.add(p to (dist + 1))
                            }
                        }
                    }
                }

                if (distances[heightmap.end] != null && distances[heightmap.end]!! < bestSteps) {
                    bestSteps = distances[heightmap.end]!!
                }
            }
        }

        println(bestSteps)
    }

    private fun buildMap(input: String): AreaMap {
        var start: Day9.Position? = null
        var end: Day9.Position? = null

        val heights = input.lines().withIndex().map { (y, it) ->
            val line = mutableListOf<Char>()

            for ((x, c) in it.toCharArray().withIndex()) {
                when (c) {
                    'S' -> {
                        start = Day9.Position(x, y)
                        line.add('a')
                    }

                    'E' -> {
                        end = Day9.Position(x, y)
                        line.add('z')
                    }

                    else -> {
                        line.add(c)
                    }
                }
            }

            line
        }

        return AreaMap(heights, start!!, end!!)
    }

    data class AreaMap(val heights: List<List<Char>>, val start: Day9.Position, val end: Day9.Position) {
        fun neighbours(position: Day9.Position): List<Day9.Position> {
            val selfHeight = heights[position.y][position.x]

            val res = mutableListOf<Day9.Position>()

            if (position.y != 0) {
                val h = heights[position.y - 1][position.x]

                if ((h - selfHeight) <= 1) {
                    res.add(Day9.Position(position.x, position.y - 1))
                }
            }

            if (position.x != 0) {
                val h = heights[position.y][position.x - 1]

                if ((h - selfHeight) <= 1) {
                    res.add(Day9.Position(position.x - 1, position.y))
                }
            }

            if (position.x != (heights[0].size - 1)) {
                val h = heights[position.y][position.x + 1]

                if ((h - selfHeight) <= 1) {
                    res.add(Day9.Position(position.x + 1, position.y))
                }
            }

            if (position.y != (heights.size - 1)) {
                val h = heights[position.y + 1][position.x]

                if ((h - selfHeight) <= 1) {
                    res.add(Day9.Position(position.x, position.y + 1))
                }
            }


            return res
        }
    }
}