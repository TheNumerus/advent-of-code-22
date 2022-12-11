import java.io.File

class Day11(private val input: String) : Solvable {
    override fun solve() {
        var monkeys = File(input).readText().split("\n\n").map(this::parseMonkey)

        for (round in 0 until 20) {
            for (m in monkeys.indices) {
                for (i in monkeys[m].items.indices) {
                    monkeys[m].inspections += 1

                    var worry = monkeys[m].items[i]
                    worry = monkeys[m].op(worry)
                    worry /= 3u
                    if (worry.mod(monkeys[m].div) == 0u) {
                        monkeys[monkeys[m].trueMonkey].items.add(worry)
                    } else {
                        monkeys[monkeys[m].falseMonkey].items.add(worry)
                    }
                }

                monkeys[m].items = mutableListOf()
            }
        }

        var sortedMB = monkeys.map { it.inspections }.sorted().takeLast(2)
        println(sortedMB[0] * sortedMB[1])

        monkeys = File(input).readText().split("\n\n").map(this::parseMonkey)

        val divisor = monkeys.map { it.div }.fold(1u) { acc: UInt, uInt: UInt -> acc * uInt }

        for (round in 0 until 10_000) {
            for (m in monkeys.indices) {
                for (i in monkeys[m].items.indices) {
                    monkeys[m].inspections += 1

                    var worry = monkeys[m].items[i]
                    worry = monkeys[m].op(worry)
                    worry %= divisor
                    if (worry.mod(monkeys[m].div) == 0u) {
                        monkeys[monkeys[m].trueMonkey].items.add(worry)
                    } else {
                        monkeys[monkeys[m].falseMonkey].items.add(worry)
                    }
                }

                monkeys[m].items = mutableListOf()
            }
        }

        sortedMB = monkeys.map { it.inspections }.sorted().takeLast(2)
        println(sortedMB[0] * sortedMB[1])
    }

    private fun parseMonkey(input: String): Monkey {
        val lines = input.lines()

        val items = lines[1].trim().split(' ', ',').mapNotNull(String::toULongOrNull).toMutableList()

        val opSplits = lines[2].trim().split(' ')

        val par2 = when (opSplits[5]) {
            "old" -> {
                ParamOld
            }

            else -> {
                ParamNum(opSplits[5].toULong())
            }
        }

        val op = constructOp(par2, opSplits[4][0])

        val div = lines[3].trim().split(' ')[3].toUInt()
        val trueMonkey = lines[4].trim().split(' ')[5].toInt()
        val falseMonkey = lines[5].trim().split(' ')[5].toInt()


        return Monkey(items, op, div, trueMonkey, falseMonkey)
    }

    private fun constructOp(par2: Param, op: Char): (ULong) -> ULong {
        when (op) {
            '+' -> {
                when (par2) {
                    is ParamNum -> {
                        return { i: ULong -> i + par2.i }
                    }

                    is ParamOld -> {
                        return { i: ULong -> i + i }
                    }
                }
            }

            '*' -> {
                when (par2) {
                    is ParamNum -> {
                        return { i: ULong -> i * par2.i }
                    }

                    is ParamOld -> {
                        return { i: ULong -> i * i }
                    }
                }
            }
        }

        throw Exception("Unknown function params")
    }

    sealed interface Param

    class ParamNum(val i: ULong) : Param
    object ParamOld : Param


    data class Monkey(
        var items: MutableList<ULong>,
        val op: (ULong) -> ULong,
        val div: UInt,
        val trueMonkey: Int,
        val falseMonkey: Int,
        var inspections: Long = 0,
    )
}