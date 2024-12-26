package ru.micromagicman.android.homework1.animal

import ru.micromagicman.android.homework1.domain.Food
import ru.micromagicman.android.homework1.domain.Gender
import ru.micromagicman.android.homework1.domain.Location

class Cat(name: String, gender: Gender) : Animal(name, gender) {

    override val ration: Set<Food> = setOf(
        Food.MEAT,
        Food.VEGETABLES
    )

    override val habitatArea: Set<Location> = setOf(
        Location.TREE,
        Location.HOUSE,
        Location.GARDEN
    )

    override val humanReadableName = "Кот"

    override fun performSound() {
        println("Мяу!")
    }
}