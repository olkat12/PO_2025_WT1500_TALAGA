fun main(args: Array<String>) {

    val bouncyMap = BouncyMap(5,5)
    val animal1 = Animal(Vector2d(1,1))
    bouncyMap.place(animal1)
    println(bouncyMap.objectAt(Vector2d(1,1)))
    bouncyMap.move(animal1, MoveDirection.RIGHT)
    println(animal1)
    bouncyMap.move(animal1, MoveDirection.FORWARD)
    println("${animal1.direction}, ${animal1.position}")

    println(bouncyMap.canMoveTo(Vector2d(5,5)))
    println(bouncyMap.canMoveTo(Vector2d(6,6)))
    println(bouncyMap.canMoveTo(Vector2d(-1,-1)))

    println(bouncyMap.isOccupied(Vector2d(2,1)))

    val animal2 = Animal(Vector2d(2,1)) // na tej samej pozycji
    bouncyMap.place(animal2) // gdzieś go wyrzuci
    println(animal2.position)

    val animal3 = Animal(Vector2d(10, 10))
    bouncyMap.place(animal3) // nie powinno położyć
    println(bouncyMap.animals.size)



}
