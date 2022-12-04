import java.io.File

class Day4(private val input: String) {
    fun solve() {
        var duplicates = File(input).readLines().sumOf {
            val (al, ah, bl, bh) = it.split(',', '-').map { r -> r.toInt() }

            if ((al >= bl && ah <= bh) || (bl >= al && bh <= ah)) {
                1L
            } else {
                0
            }
        }

        println(duplicates)

        duplicates = File(input).readLines().sumOf {
            val (al, ah, bl, bh) = it.split(',', '-').map { r -> r.toInt() }

            if ((bl in al..ah) || (bh in al..ah) || (ah in bl..bh) || (al in bl..bh)) {
                1L
            } else {
                0
            }
        }

        println(duplicates)
    }
}