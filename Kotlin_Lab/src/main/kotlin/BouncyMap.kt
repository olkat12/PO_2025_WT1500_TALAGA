class BouncyMap(val width: Int, val height: Int) : WorldMap {

    val LOWER_BOUNDARY = Vector2d(0,0)
    private val _animals = mutableMapOf<Vector2d, Animal>() // prywatna mapa

    val animals : Map<Vector2d, Animal>  // tylko do odczytu
        get() = _animals


    override fun objectAt(position: Vector2d): Animal? = _animals[position]

    override fun isOccupied(position: Vector2d) = position in animals //animals.containsKey(position)

    override fun place(animal: Animal) {

        if (!canMoveTo(animal.position)) return

        _animals.values.removeIf { it === animal }

        if(isOccupied(animal.position)){
            val targetPos = _animals
                .randomFreePosition(Vector2d(width, height))
                ?: _animals
                    .randomPosition()
                    ?.also { _animals.remove(it) }

            targetPos?.let { animal.position = it }
            //if (targetPos != null) animal.position = targetPos
        }
        _animals[animal.position] = animal

    }


    override fun move(animal: Animal, direction: MoveDirection) {

        val oldPos = animal.position

        // Czy zwierzak na mapie
        if (_animals[oldPos] === animal) {
            animal.move(direction, this)
            if (oldPos != animal.position) {
                _animals.remove(oldPos)
                place(animal)
            }
        }

    }

    override fun canMoveTo(position: Vector2d): Boolean {
        return position <= Vector2d(width-1, height-1) && position >= LOWER_BOUNDARY
    }

}