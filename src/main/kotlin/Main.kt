import kotlin.math.abs
import kotlin.random.Random

fun main() {
//    randomBattleField()
    createBattleField()
}

fun randomBattleField() {
    val field = List(3) {
        Array(3) { if (Random.nextBoolean()) "X" else "O" }
    }
    println("It's random battle field")
    field.forEach{ println(it.joinToString(" ")) }

}

fun createBattleField() {
    println("\nTo play, please enter sequence of 9: \"X\", \"0\" or \"_\"")
    val sequence = readln().split("").toMutableList()
    sequence.removeFirst()
    sequence.removeLast()
    val field = listOf(
        sequence.subList(0,3), sequence.subList(3,6), sequence.subList(6,9)
    )
    println(
        """
        It's battle of field
        ---------
        | ${field[0].joinToString(" ")} |
        | ${field[1].joinToString(" ")} |
        | ${field[2].joinToString(" ")} |
        ---------
        """.trimIndent()
    )
    println(checkWinner(sequence))
}

fun checkWinner(sequence: MutableList<String>): String {
    val xWin = isWin(sequence, "X")
    val oWin = isWin(sequence, "O")

    return when {
        xWin && oWin || abs(sequence.count { it == "X" } - sequence.count { it == "O" }) >= 2 -> "Impossible"
        xWin -> "X Win"
        oWin -> "O Win"
        sequence.count { it == "X" } + sequence.count { it == "O" } == 9 -> "Draw"

        else -> "Game not finished"
    }
}

fun isWin (sequence: MutableList<String>, value: String): Boolean =
    (sequence[0] == value && ((sequence[3] == value && sequence[6] == value|| sequence.subList(1, 3).joinToString("") == value + value) || (sequence[4] == value && sequence[8] == value))) ||
            (sequence[1] == value && (sequence[4] == value && sequence[7] == value)) ||
            (sequence[2] == value && (sequence[5] == value && sequence[8] == value || (sequence[4] == value && sequence[6] == value))) ||
            (sequence[3] == value && sequence.subList(4, 6).joinToString("") == value + value) ||
            (sequence[6] == value && sequence.subList(6, 9).joinToString("") == value + value)
