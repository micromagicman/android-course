package ru.micromagicman.android.homework1.state

import ru.micromagicman.android.homework1.animal.Animal
import ru.micromagicman.android.homework1.domain.Location
import ru.micromagicman.android.homework1.exception.IllegalLocationException
import ru.micromagicman.android.homework1.util.randomConstant

data object WalkingState : AnimalState() {

    override fun execute(target: Animal) {
        val location = Location::class.randomConstant()
        if (!target.habits(location)) {
            throw IllegalLocationException(target, location)
        }
        println("$target гуляет в локации $location")
    }
}