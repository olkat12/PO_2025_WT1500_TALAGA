import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class Vector2dTest : FunSpec({

    val v1 = Vector2d(1,5)
    val v2 = Vector2d(3,4)
    val v3 = Vector2d(0, 1)

    test("plus operator should add two vectors"){
        v1 + v2 shouldBe  Vector2d(4,9)
    }

    test("minus operator should substract two vectors"){
        v1 - v2 shouldBe  Vector2d(-2,1)
    }

    test("opposite should return correct vector"){
        v1.opposite() shouldBe Vector2d(-1,-5)
    }

    test("upperRight and lowerLeft"){
        v1.upperRight(v2) shouldBe Vector2d(3,5)
        v1.lowerLeft(v2) shouldBe Vector2d(1,4)
    }

    test("MapDirection.toUnitVector should return correct vectors"){
        MapDirection.NORTH.toUnitVector() shouldBe Vector2d(0, 1)
        MapDirection.EAST.toUnitVector() shouldBe Vector2d(1, 0)
        MapDirection.SOUTH.toUnitVector() shouldBe Vector2d(0, -1)
        MapDirection.WEST.toUnitVector() shouldBe Vector2d(-1, 0)
    }

    test("v2 > v3 when true"){
        (v2 > v3) shouldBe true
    }

    test("v3 > v2 when false"){
        (v3 > v2) shouldBe false
    }

    test("v3 < v2 when true"){
        (v2 > v3) shouldBe true
    }

})