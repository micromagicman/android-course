package ru.micromagicman.android.homework1.state

import ru.micromagicman.android.homework1.animal.Animal
import ru.micromagicman.android.homework1.util.newRandomImplementationInstance

sealed class AnimalState {

    abstract fun execute(target: Animal)
}

fun randomState(): AnimalState {
    return AnimalState::class.newRandomImplementationInstance()
}