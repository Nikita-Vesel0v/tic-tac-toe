import kotlin.random.Random

fun main() {
    randomBattleField()
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
        It's battle field"
        ---------
        | ${field[0].joinToString(" ")} |
        | ${field[1].joinToString(" ")} |
        | ${field[2].joinToString(" ")} |
        ---------
        """.trimIndent()
    )
}