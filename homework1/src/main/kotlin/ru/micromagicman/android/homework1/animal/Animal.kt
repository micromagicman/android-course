package ru.micromagicman.android.homework1.animal

import ru.micromagicman.android.homework1.domain.Food
import ru.micromagicman.android.homework1.domain.Gender
import ru.micromagicman.android.homework1.domain.Location
import ru.micromagicman.android.homework1.state.AnimalState
import ru.micromagicman.android.homework1.state.randomState

/**
 * Абстракция над всеми типами животных, находящихся в зоопарке.
 */
sealed class Animal(private val name: String, private val gender: Gender) {

    /**
     * Человекочитаемое название вида животного.
     */
    abstract val humanReadableName: String

    /**
     * Рацион в виде множества типов еды.
     */
    abstract val ration: Set<Food>

    /**
     * Ареал обитания животного.
     */
    abstract val habitatArea: Set<Location>

    /**
     * Состояние животного.
     */
    open var state: AnimalState = randomState()

    /**
     * Издать звук.
     */
    abstract fun performSound()

    /**
     * Животное выоплняет действие, зависящее от его состояния.
     */
    fun act() {
        state.execute(this)
    }

    /**
     * Предикат, опередляющий, входит ли переданный тип пищи в рацион данного животного.
     */
    fun eats(food: Food): Boolean {
        return ration.contains(food)
    }

    /**
     * Предикат, опередляющий, входит ли переданный тип локации в ареал обитания животного.
     */
    fun habits(location: Location): Boolean {
        return habitatArea.contains(location)
    }

    /**
     * Строковое представление животного.
     */
    override fun toString(): String {
        return "$name ($humanReadableName ${gender.toString().lowercase()})"
    }
}