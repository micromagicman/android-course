package ru.micromagicman.android.homework1

import ru.micromagicman.android.homework1.animal.Animal
import ru.micromagicman.android.homework1.animal.RandomizedAnimalFactory
import ru.micromagicman.android.homework1.state.DoingNothingState
import ru.micromagicman.android.homework1.state.EatingState
import ru.micromagicman.android.homework1.state.SleepingState
import ru.micromagicman.android.homework1.state.WalkingState
import kotlin.math.max
import kotlin.math.min

const val MAX_ANIMALS_VALUE = 500
const val DEFAULT_ANIMALS_VALUE = 20

fun main(args: Array<String>) {
    Zoo(RandomizedAnimalFactory(), resolveAnimalsCount(args))
        .use {
            println("Ревизия животных")
            println("Общее количество животных: ${it.animalsCount()}")
            for (animalType in Animal::class.sealedSubclasses) {
                println("Список животных типа ${animalType.simpleName}: ${it.getAnimalsOfType(animalType)}")
            }
            println("Спящие животные: ${it.getAnimalsWithState(SleepingState)}")
            println("Гуляющие животные: ${it.getAnimalsWithState(WalkingState)}")
            println("Кушающие животные: ${it.getAnimalsWithState(EatingState)}")
            println("Бездельничающие животные: ${it.getAnimalsWithState(DoingNothingState)}")

            it.open()
            it.doSomeWork()
        }
}

fun resolveAnimalsCount(args: Array<String>): Int {
    if (args.isEmpty()) {
        return DEFAULT_ANIMALS_VALUE
    }
    return try {
        max(1, min(args[0].toInt(), MAX_ANIMALS_VALUE))
    } catch (exception: NumberFormatException) {
        DEFAULT_ANIMALS_VALUE
    }
}
