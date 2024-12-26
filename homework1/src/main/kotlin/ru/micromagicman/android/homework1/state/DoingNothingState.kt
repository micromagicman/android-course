package ru.micromagicman.android.homework1.state

import ru.micromagicman.android.homework1.animal.Animal

/**
 * Состояние "ничего-не-делания".
 */
data object DoingNothingState : AnimalState() {

    override fun execute(target: Animal) {
        throw IllegalStateException("$target ничего не делает, непорядок!")
    }
}