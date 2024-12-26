package ru.micromagicman.android.homework1.animal

import ru.micromagicman.android.homework1.domain.Food
import ru.micromagicman.android.homework1.domain.Gender
import ru.micromagicman.android.homework1.domain.Location

class Dog(name: String, gender: Gender) : Animal(name, gender) {

    override val ration: Set<Food> = setOf(Food.MEAT)

    override val habitatArea: Set<Location> = setOf(
        Location.HOUSE,
        Location.GARDEN,
        Location.FOREST
    )

    override val humanReadableName = "Собака"

    override fun performSound() {
        println("Гав!")
    }
}