package ru.micromagicman.android.homework1.state

import ru.micromagicman.android.homework1.animal.Animal

class EscapeFromCageState : ZooTerminatingAnimalState() {

    override fun message(target: Animal): String {
        return "[$target]: сбежал из клетки"
    }
}