import java.io.File

class Day6(private val input: String) : Solvable {
    override fun solve() {
        var chars = File(input).readText().windowed(4).withIndex()

        for ((i, packet) in chars) {
            if (packet[0] != packet[1] && packet[1] != packet[2] && packet[2] != packet[3] && packet[0] != packet[2] && packet[0] != packet[3] && packet[1] != packet[3]) {
                println(i + 4)
                break
            }
        }

        chars = File(input).readText().windowed(14).withIndex()

        for ((i, packet) in chars) {
            var matches = true

            for (x in 0 until 14) {
                for (y in 0 until 14) {
                    if (x != y && packet[x] == packet[y]) {
                        matches = false
                    }
                }
            }

            if (matches) {
                println(i + 14)
                break
            }
        }
    }
}