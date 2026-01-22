
fun <V> Map<Vector2d, V>.randomPosition(): Vector2d? = keys.randomOrNull()

fun <V> Map<Vector2d, V>.randomFreePosition(mapSize: Vector2d): Vector2d? {

    val allGridPositions = mutableListOf<Vector2d>()
    for (x in 0 until mapSize.x) {
        for (y in 0 until mapSize.y) {
            allGridPositions.add(Vector2d(x, y))
        }
    }

    return allGridPositions
        .filter { it !in this } //!this.containsKey(it)
        .randomOrNull()
}