import MapDirection.EAST
import MapDirection.NORTH
import MapDirection.SOUTH
import MapDirection.WEST
import kotlin.math.max
import kotlin.math.min

data class Vector2d(val x: Int, val y: Int) {

    override fun toString() = "($x, $y)"

    operator fun plus(other: Vector2d) = Vector2d(x + other.x, y + other.y)

    operator fun minus(other: Vector2d) = Vector2d(x - other.x, y - other.y)

    operator fun compareTo(other: Vector2d) : Int = when {
        x == other.x && y == other.y -> 0
        x <= other.x && y <= other.y -> -1
        x >= other.x && y >= other.y -> 1
        else -> 0
    }

    fun opposite() = Vector2d(-x, -y)

    fun upperRight(other: Vector2d) = Vector2d(max(x, other.x), max(y, other.y))

    fun lowerLeft(other: Vector2d) = Vector2d(min(x, other.x), min(y, other.y))
}

fun MapDirection.toUnitVector() = when (this) {
    NORTH -> Vector2d(0, 1)
    EAST -> Vector2d(1, 0)
    SOUTH -> Vector2d(0, -1)
    WEST -> Vector2d(-1, 0)
}