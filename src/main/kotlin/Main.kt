import kotlin.random.Random

fun main() {
    battleField()
}

fun battleField() {
    val field = List(3) {
        Array(3) { if (Random.nextBoolean()) "X" else "O" }
    }

    field.forEach{ println(it.joinToString(" ")) }

}