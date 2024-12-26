package ru.micromagicman.android.homework1.state

import ru.micromagicman.android.homework1.animal.Animal

data object SleepingState : AnimalState() {

    override fun execute(target: Animal) {
        println("[$target]: засыпает Z-z-z-z-z-z-z")
    }
}