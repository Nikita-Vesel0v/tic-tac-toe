import kotlin.random.Random

var field = createBattleField()

fun main() {

    printField()
    var resultPlay = checkWinner()
    var xOrO = 9
    while (resultPlay == "Game not finished") {
        makeMove(xOrO % 2 == 1)
        printField()
        resultPlay = checkWinner()
        if (resultPlay != "Game not finished") println(resultPlay) else xOrO --
    }
}

fun createBattleField(random: String = "No random"): List<MutableList<String>> =
    if (random != "random") List(3) { MutableList(3) { " " } }
    else List(3) { MutableList(3) { if (Random.nextBoolean()) "X" else "O" } }

fun checkWinner(): String {
    fun countOfElement(value: String): Int {
        var sum = 0; field.forEach { sum += it.count { valueElem -> valueElem == value } }
        return sum
    }
    fun isWin (value: String): Boolean =
        (field[0][0] == value && ((field[1][0] == value && field[2][0] == value) || (field[0][1] == value && field[0][2] == value) || (field[1][1] == value && field[2][2] == value))) ||
                (field[0][1] == value && (field[1][1] == value && field[2][1] == value)) ||
                (field[0][2] == value && (field[1][2] == value && field[2][2] == value || (field[1][1] == value && field[2][0] == value))) ||
                (field[1][0] == value && field[1][1] == value && field[1][2] == value) ||
                (field[2][0] == value && field[2][1] == value && field[2][2] == value)

    return when {
        isWin("X") -> "X wins"
        isWin("O") -> "O wins"
        countOfElement("X") + countOfElement("O") == 9 -> "Draw"

        else -> "Game not finished"
    }
}

fun makeMove(isX: Boolean) {
    while(true) {
        try {
            val (i, j) = readln().split(" ").map { it.toInt() }
            when {
                i > 3 || j > 3 || i < 1 || j < 1 -> println("Coordinates should be from 1 to 3!")
                field[i - 1][j - 1] == "O" || field[i - 1][j - 1] == "X" -> println("This cell is occupied! Choose another one!")
                else -> {
                    field[i - 1][j - 1] = if (isX) "X" else "O"
                    break
                }
            }
        } catch (e: NumberFormatException) {
            println("You should enter numbers!")
        }
    }
}

fun printField() {
    println("---------")
    field.forEach { println("| ${it.joinToString(" ")} |")}
    println("---------")
}