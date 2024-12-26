package ru.micromagicman.android.homework1.animal

import ru.micromagicman.android.homework1.domain.Food
import ru.micromagicman.android.homework1.domain.Gender
import ru.micromagicman.android.homework1.domain.Location

class Monkey(name: String, gender: Gender) : Animal(name, gender) {

    override val ration: Set<Food> = setOf(
        Food.VEGETABLES,
        Food.FRUITS
    )

    override val habitatArea: Set<Location> = setOf(
        Location.TREE,
        Location.FOREST
    )

    override val humanReadableName = "Обезьяна"

    override fun performSound() {
        println("У-у-у-а-а-а-а")
    }
}