import java.io.File
import kotlin.math.absoluteValue
import kotlin.math.sign

class Day9(private val input: String) : Solvable {
    override fun solve() {
        val moves = File(input).readLines().map { it.split(' ') }

        val head = Position(0, 0)
        val tail = Position(0, 0)

        val visited = mutableSetOf(Position(0, 0))

        for ((dir, steps) in moves) {
            val stepsInt = steps.toInt()
            for (step in 0 until stepsInt) {
                when (dir) {
                    "L" -> {
                        head.x -= 1
                    }

                    "U" -> {
                        head.y += 1
                    }

                    "R" -> {
                        head.x += 1
                    }

                    "D" -> {
                        head.y -= 1
                    }
                }

                val deltaX = head.x - tail.x
                val deltaY = head.y - tail.y

                if (deltaX.absoluteValue >= 2 || deltaY.absoluteValue >= 2) {
                    tail.x += deltaX.sign
                    tail.y += deltaY.sign
                }

                visited.add(tail.copy())
            }
        }

        println(visited.size)
        visited.clear()
        visited.add(Position(0, 0))

        val rope = List(10) { Position(0, 0) }

        for ((dir, steps) in moves) {
            val stepsInt = steps.toInt()
            for (step in 0 until stepsInt) {
                when (dir) {
                    "L" -> {
                        rope[0].x -= 1
                    }

                    "U" -> {
                        rope[0].y += 1
                    }

                    "R" -> {
                        rope[0].x += 1
                    }

                    "D" -> {
                        rope[0].y -= 1
                    }
                }

                for (h in 0 until (rope.size - 1)) {
                    val t = h + 1

                    val deltaX = rope[h].x - rope[t].x
                    val deltaY = rope[h].y - rope[t].y

                    if (deltaX.absoluteValue >= 2 || deltaY.absoluteValue >= 2) {
                        rope[t].x += deltaX.sign
                        rope[t].y += deltaY.sign
                    }

                    if (t == 9) {
                        visited.add(rope[t].copy())
                    }
                }
            }
        }

        println(visited.size)
    }

    data class Position(var x: Int, var y: Int)
}