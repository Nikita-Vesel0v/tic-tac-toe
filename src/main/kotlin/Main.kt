import kotlin.math.abs
import kotlin.random.Random

fun main() {
    var field = randomBattleField()
    printField(field)
    val sequence = readSequence()
    field = createBattleField(sequence)
    printField(field)
    var resultPlay = checkWinner(field)
    println(resultPlay)
    if (resultPlay == "Game not finished") {
        field = makeMove(field)
        printField(field)
        resultPlay = checkWinner(field)
        println(resultPlay)
    }
}

fun randomBattleField(): List<MutableList<String>> =
    List(3) {
        MutableList(3) { if (Random.nextBoolean()) "X" else "O" }
    }

fun createBattleField(sequence: MutableList<String>): List<MutableList<String>> =
     listOf (
        sequence.subList(0, 3),
        sequence.subList(3, 6),
        sequence.subList(6, 9)
    )

fun checkWinner(field: List<MutableList<String>>): String {
    fun countOfElement(value: String): Int {
        var sum = 0; field.forEach { sum += it.count { valueElem -> valueElem == value } }
        return sum
    }
    fun isWin (field: List<MutableList<String>>, value: String): Boolean =
        (field[0][0] == value && ((field[1][0] == value && field[2][0] == value) || (field[0][1] == value && field[0][2] == value) || (field[1][1] == value && field[2][2] == value))) ||
                (field[0][1] == value && (field[1][1] == value && field[2][1] == value)) ||
                (field[0][2] == value && (field[1][2] == value && field[2][2] == value || (field[1][1] == value && field[2][0] == value))) ||
                (field[1][0] == value && field[1][1] == value && field[1][2] == value) ||
                (field[2][0] == value && field[2][1] == value && field[2][2] == value)

    val xWin = isWin(field, "X")
    val oWin = isWin(field, "O")
    val countX = countOfElement("X")
    val countO = countOfElement("O")

    return when {
        xWin && oWin || abs(countX - countO) >= 2 -> "Impossible"
        xWin -> "X Win"
        oWin -> "O Win"
        countO + countX == 9 -> "Draw"

        else -> "Game not finished"
    }
}

fun makeMove(field: List<MutableList<String>>): List<MutableList<String>> {
    while(true) {
        try {
            val (i, j) = readln().split(" ").map { it.toInt() }
            when {
                i > 3 || j > 3 || i < 1 || j < 1 -> println("Coordinates should be from 1 to 3!")
                field[i - 1][j - 1] == "O" -> println("This cell is occupied! Choose another one!")
                else -> {
                    field[i - 1][j - 1] = "X"
                    break
                }

            }
        } catch (e: Exception) {
            println("You should enter numbers!")
        }
    }
    return field
}

fun printField(field: List<MutableList<String>>) {
    for(i in field.indices) {
        field[i].forEach { if (it == "_") field[i][field[i].indexOf(it)] = " "}
    }
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
}

fun readSequence(): MutableList<String> {
    println("\nTo play, please enter sequence of 9: \"X\", \"0\" or \"_\"")
    val sequence = readln().split("").toMutableList()
    sequence.removeFirst()
    sequence.removeLast()
    return sequence
}