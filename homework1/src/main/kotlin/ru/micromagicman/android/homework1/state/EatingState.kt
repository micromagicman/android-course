package ru.micromagicman.android.homework1.state

import ru.micromagicman.android.homework1.animal.Animal
import ru.micromagicman.android.homework1.domain.Food
import ru.micromagicman.android.homework1.exception.IllegalFoodException
import ru.micromagicman.android.homework1.util.randomConstant

/**
 * Состояние поедания животным чего-либо.
 */
data object EatingState : AnimalState() {

    /**
     * Тип еды, которое будет есть животное.
     */
    override fun execute(target: Animal) {
        val food: Food = Food::class.randomConstant()
        if (!target.eats(food)) {
            throw IllegalFoodException(target, food)
        }
        println("[$target] ест $food")
    }
}