enum class MapDirection {

    NORTH,
    EAST,
    SOUTH,
    WEST;

    override fun toString(): String {
        return when(this) {
            NORTH -> "Polnoc"
            EAST -> "Wschod"
            SOUTH -> "Poludnie"
            WEST -> "Zachod"
        }
    }

    fun next() : MapDirection = when (this) {
        NORTH -> EAST
        EAST -> SOUTH
        SOUTH -> WEST
        WEST -> NORTH
    }

    fun previous() : MapDirection = when (this) {
        NORTH -> WEST
        EAST -> NORTH
        SOUTH -> EAST
        WEST -> SOUTH
    }



}