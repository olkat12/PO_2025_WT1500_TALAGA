enum class MapDirection {

    NORTH,
    EAST,
    SOUTH,
    WEST;

    override fun toString() = when(this) {
            NORTH -> "Polnoc"
            EAST -> "Wschod"
            SOUTH -> "Poludnie"
            WEST -> "Zachod"
    }

    fun next() = when (this) {
        NORTH -> EAST
        EAST -> SOUTH
        SOUTH -> WEST
        WEST -> NORTH
    }

    fun previous() = when (this) {
        NORTH -> WEST
        EAST -> NORTH
        SOUTH -> EAST
        WEST -> SOUTH
    }



}