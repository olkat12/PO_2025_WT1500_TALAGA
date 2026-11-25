import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class BouncyMapTest : FunSpec({

    // Dla każdego testu tworzy zupełnie nową instancję klasy testowej
    isolationMode = IsolationMode.InstancePerTest

    val map = BouncyMap(5, 5)

    test("objectAt when null returns null and when animal returns animal"){
        map.objectAt(Vector2d(1,3)) shouldBe null

        val animal = Animal(Vector2d(1,1))
        map.place(animal)
        map.objectAt(Vector2d(1,1)) shouldBe animal
    }

    test("isOccupied when null returns false and when animal returns true"){
        map.isOccupied(Vector2d(1,3)) shouldBe false

        val animal = Animal(Vector2d(1,1))
        map.place(animal)
        map.isOccupied(Vector2d(1,1)) shouldBe true

    }

    test("canMoveTo should stay in boundaries") {
        map.canMoveTo(Vector2d(0, 0)) shouldBe true
        map.canMoveTo(Vector2d(3, 5)) shouldBe true

        map.canMoveTo(Vector2d(-1, -1)) shouldBe false
        map.canMoveTo(Vector2d(10, 10)) shouldBe false
    }

    test("place should add animal correctly") {
        val animal = Animal(Vector2d(2, 2))
        map.place(animal)
        map.objectAt(Vector2d(2, 2)) shouldBe animal

        animal.position = Vector2d(3, 3)
        map.place(animal)

        map.objectAt(Vector2d(2, 2)) shouldBe null
        map.objectAt(Vector2d(3, 3)) shouldBe animal
    }

    test("animal should bounce to a random free position when place is occupied") {
        val smallMap = BouncyMap(2, 1)
        val animal1 = Animal(Vector2d(0, 0))
        val animal2 = Animal(Vector2d(0, 0))

        smallMap.place(animal1)
        smallMap.place(animal2)

        smallMap.objectAt(Vector2d(0, 0)) shouldBe animal1
        smallMap.objectAt(Vector2d(1, 0)) shouldBe animal2
        animal2.position shouldBe Vector2d(1, 0)
    }

    test("animal should bounce another animal if the map is full") {
        val tinyMap = BouncyMap(1, 1)
        val animal1 = Animal(Vector2d(0, 0))
        val animal2 = Animal(Vector2d(0, 0))

        tinyMap.place(animal1)
        tinyMap.place(animal2) // animal2 powinien wybić animal1

        tinyMap.animals.size shouldBe 1
        tinyMap.objectAt(Vector2d(0, 0)) shouldBe animal2
        tinyMap.objectAt(Vector2d(0, 0)) shouldNotBe animal1
    }

    test("moving animal should bounce if moving into another animal") {
        val animal1 = Animal(Vector2d(1, 2))
        val animal2 = Animal(Vector2d(1, 1))

        map.place(animal1)
        map.place(animal2)

        map.move(animal2, MoveDirection.FORWARD) // wchodzi na zwierzaka

        animal2.position shouldNotBe Vector2d(1, 1)
        animal2.position shouldNotBe Vector2d(1, 2)
        map.objectAt(Vector2d(1, 2)) shouldBe animal1
    }
})