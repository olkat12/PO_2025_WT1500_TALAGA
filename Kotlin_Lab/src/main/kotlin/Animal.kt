
fun String.toMoveDirection() : MoveDirection? = when (this) {
    "f" -> MoveDirection.FORWARD
    "b" -> MoveDirection.BACKWARD
    "r" -> MoveDirection.RIGHT
    "l" -> MoveDirection.LEFT
    else -> null
}

fun parseOptions(args: Array<String>) : List<MoveDirection> = args.mapNotNull {it.toMoveDirection()}


class Animal(position : Vector2d = Vector2d(0,0)) {

    var direction: MapDirection = MapDirection.NORTH
        private set

    var position = position
        internal set

    internal fun move(movement : MoveDirection, validator : MoveValidator){

        this.direction = when(movement){
            MoveDirection.RIGHT -> direction.next()
            MoveDirection.LEFT -> direction.previous()
            else -> direction
        }

        val unitVector = direction.toUnitVector()
        val nextPosition = when(movement){
            MoveDirection.FORWARD -> position + unitVector
            MoveDirection.BACKWARD -> position - unitVector
            else -> position
        }

        if(validator.canMoveTo(nextPosition)){
            this.position = nextPosition
        }

    }

    override fun toString() = when(direction) {
        MapDirection.NORTH -> "^"
        MapDirection.EAST -> ">"
        MapDirection.SOUTH -> "v"
        MapDirection.WEST -> "<"
    }
}



