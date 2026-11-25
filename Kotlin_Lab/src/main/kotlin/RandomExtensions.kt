
fun <V> Map<Vector2d, V>.randomPosition(): Vector2d? = keys.randomOrNull()

fun <V> Map<Vector2d, V>.randomFreePosition(mapSize: Vector2d): Vector2d? =

    (0 until mapSize.x).asSequence()
        .flatMap { x -> (0 until mapSize.y).asSequence().map { y -> Vector2d(x, y) } }
        .filter { it !in this } //!this.containsKey(it)
        .shuffled()
        .firstOrNull()

