class BouncyMap(val width: Int, val height: Int) : WorldMap {

    val LOWER_BOUNDARY = Vector2d(0,0)
    private val _animals = mutableMapOf<Vector2d, Animal>() // prywatna mapa

    val animals : Map<Vector2d, Animal>  // tylko do odczytu
        get() = _animals




    override fun objectAt(position: Vector2d): Animal? = _animals[position]

    override fun isOccupied(position: Vector2d): Boolean = objectAt(position) != null


    override fun place(animal: Animal) {

        if (!canMoveTo(animal.position)) return

        _animals.values.removeIf { it === animal }

        if (isOccupied(animal.position)) {
            val freePos = _animals.randomFreePosition(Vector2d(width, height))
            if (freePos != null) {
                // nowa wolna pozycja
                animal.position = freePos
            }
            else {
                // wybicie zwierzaka
                _animals.randomPosition()?.let { victimPos ->
                    _animals.remove(victimPos)
                    animal.position = victimPos
                }
            }
        }

        _animals[animal.position] = animal
    }



    override fun move(animal: Animal, direction: MoveDirection) {

        val oldPos = animal.position

        // Czy zwierzak na mapie
        if(animal == objectAt(oldPos)){
            animal.move(direction, this)
            _animals.remove(oldPos)
            place(animal)
        }

    }


    override fun canMoveTo(position: Vector2d): Boolean {
        return position <= Vector2d(width, height) && position >= LOWER_BOUNDARY
    }


}