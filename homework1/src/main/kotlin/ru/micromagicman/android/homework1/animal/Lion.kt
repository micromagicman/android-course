package ru.micromagicman.android.homework1.animal

import ru.micromagicman.android.homework1.domain.Food
import ru.micromagicman.android.homework1.domain.Gender
import ru.micromagicman.android.homework1.domain.Location
import ru.micromagicman.android.homework1.state.AnimalState
import ru.micromagicman.android.homework1.state.EscapeFromCageState
import ru.micromagicman.android.homework1.state.SleepingState
import ru.micromagicman.android.homework1.state.randomState
import kotlin.random.Random

val escapeFromCage: AnimalState by lazy { EscapeFromCageState() }

class Lion(name: String, gender: Gender) : Animal(name, gender) {

    override val ration: Set<Food> = setOf(Food.MEAT)

    override val habitatArea: Set<Location> = setOf(
        Location.FOREST,
        Location.SAVANNAH
    )

    override var state: AnimalState = randomState()
        set(newValue) {
            // С вероятностью около 1% лев сбежит из клетки и зоопарк придется экстренно закрыть.
            val nextInt = Random(hashCode()).nextInt(0, 100)
            field = if (newValue != SleepingState && nextInt > 98) {
                escapeFromCage
            } else {
                newValue
            }
        }

    override val humanReadableName = "Лев"

    override fun performSound() {
        println("[$this] Рррррррррррррр")
    }
}