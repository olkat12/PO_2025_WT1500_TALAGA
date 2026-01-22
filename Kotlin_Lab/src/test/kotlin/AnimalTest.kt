import io.kotest.core.spec.IsolationMode
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class AnimalTest : FunSpec( {

    // Dla każdego testu tworzy zupełnie nową instancję klasy testowej
    isolationMode = IsolationMode.InstancePerTest
    val map = BouncyMap(5, 5)
    val position = Vector2d(1,1)

    test("move when only changing direction"){
        val animal = Animal(position)
        map.place(animal)
        animal.direction shouldBe MapDirection.NORTH

        animal.move(MoveDirection.RIGHT, map)
        animal.direction shouldBe MapDirection.EAST

        animal.move(MoveDirection.RIGHT, map)
        animal.move(MoveDirection.LEFT, map)
        animal.direction shouldBe MapDirection.EAST
    }

    test("move on free and proper position"){
        val animal = Animal(position)
        map.place(animal)
        animal.move(MoveDirection.FORWARD, map)
        animal.position shouldBe Vector2d(1,2)
        animal.direction shouldBe MapDirection.NORTH
    }

    test("try place animal beyond map"){
        val animal = Animal(Vector2d(10, 10))
        map.place(animal)
        map.animals.size shouldBe 0
    }

})