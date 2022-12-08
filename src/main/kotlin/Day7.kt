import java.io.File

class Day7(private val input: String) : Solvable {
    override fun solve() {
        val log = File(input).readLines()

        val root = Dir()

        val stack = ArrayDeque(listOf(root))

        for (line in log) {
            val splits = line.split(' ')

            val cursor = stack.last()

            when (splits[0]) {
                "$" -> {
                    when (splits[1]) {
                        "cd" -> {
                            when (splits[2]) {
                                ".." -> {
                                    stack.removeLast()
                                }

                                "/" -> {
                                    stack.clear()
                                    stack.add(root)
                                }

                                else -> {
                                    stack.add(cursor.dirs.getOrDefault(splits[2], Dir()))
                                }
                            }
                        }
                    }
                }

                "dir" -> {
                    cursor.dirs[splits[1]] = Dir()
                }

                else -> {
                    cursor.files[splits[1]] = splits[0].toLong()
                }
            }
        }

        val (_, count) = countSizes(root)

        println(count)

        val totalSize = 70_000_000

        val (totalUsedSize, sizes) = listSizes(root)

        val min = sizes.map { Pair(it, (totalSize - totalUsedSize) + it) }.filter { (_, it) -> it > 30_000_000 }
            .map { (it, _) -> it }.min()

        println(min)
    }

    private fun countSizes(dir: Dir): Pair<Long, Long> {
        var sizeTotal = 0L
        var countTotal = 0L

        for (d in dir.dirs.values) {
            val (size, count) = countSizes(d)

            sizeTotal += size
            countTotal += count
        }

        for (f in dir.files.values) {
            sizeTotal += f
        }

        if (sizeTotal < 100000) {
            countTotal += sizeTotal
        }

        return Pair(sizeTotal, countTotal)
    }

    private fun listSizes(dir: Dir): Pair<Long, List<Long>> {
        var sizeTotal = 0L
        val sizesTotal = mutableListOf<Long>()

        for (d in dir.dirs.values) {
            val (size, sizes) = listSizes(d)

            sizeTotal += size
            sizesTotal.addAll(sizes)
        }

        for (f in dir.files.values) {
            sizeTotal += f
        }

        sizesTotal.add(sizeTotal)

        return Pair(sizeTotal, sizesTotal)
    }

    class Dir {
        val dirs = mutableMapOf<String, Dir>()
        val files = mutableMapOf<String, Long>()
    }
}
