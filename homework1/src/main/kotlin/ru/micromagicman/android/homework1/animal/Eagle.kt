package ru.micromagicman.android.homework1.animal

import ru.micromagicman.android.homework1.domain.Food
import ru.micromagicman.android.homework1.domain.Gender
import ru.micromagicman.android.homework1.domain.Location

class Eagle(name: String, gender: Gender) : Animal(name, gender) {

    override val humanReadableName: String = "Орёл"

    override val ration: Set<Food> = setOf(Food.MEAT)

    override val habitatArea: Set<Location> = setOf(
        Location.SAVANNAH,
        Location.FOREST
    )

    override fun performSound() {
        println("*Клёкот орла*")
    }
}