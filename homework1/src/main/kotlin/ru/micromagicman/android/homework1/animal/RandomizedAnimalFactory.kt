package ru.micromagicman.android.homework1.animal

import ru.micromagicman.android.homework1.domain.Gender
import ru.micromagicman.android.homework1.util.newRandomImplementationInstance
import ru.micromagicman.android.homework1.util.randomConstant

/**
 * Рандомизированная фабрика животных.
 */
class RandomizedAnimalFactory : AnimalFactory {

    override fun newAnimal(): Animal {
        val gender = Gender::class.randomConstant()
        return Animal::class.newRandomImplementationInstance(gender.randomName(), gender)
    }
}