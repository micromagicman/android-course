package ru.micromagicman.android.homework1.state

import ru.micromagicman.android.homework1.animal.Animal
import ru.micromagicman.android.homework1.exception.ZooDangerException

/**
 * Если одно из животных принимает данное состояние, то дальнейшее функционирование зоопарка невозможно.
 */
abstract class ZooTerminatingAnimalState : AnimalState() {

    override fun execute(target: Animal) {
        target.performSound()
        throw ZooDangerException(message(target))
    }

    abstract fun message(target: Animal): String
}