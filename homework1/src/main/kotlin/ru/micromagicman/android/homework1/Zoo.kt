package ru.micromagicman.android.homework1

import ru.micromagicman.android.homework1.animal.Animal
import ru.micromagicman.android.homework1.animal.AnimalFactory
import ru.micromagicman.android.homework1.exception.ZooDangerException
import ru.micromagicman.android.homework1.state.AnimalState
import ru.micromagicman.android.homework1.state.DoingNothingState
import ru.micromagicman.android.homework1.state.SleepingState
import ru.micromagicman.android.homework1.state.randomState
import ru.micromagicman.android.homework1.util.wrap
import kotlin.reflect.KClass

/**
 * Зоопарк с набором животных, создаваемых фабрикой заданного количества штук.
 */
class Zoo(factory: AnimalFactory, animalsCount: Int) : AutoCloseable {

    private val animals: List<Animal> = buildList {
        for (i in 1..animalsCount) {
            add(factory.newAnimal())
        }
    }

    /**
     * Получить подможество животных заданного типа.
     */
    fun getAnimalsOfType(type: KClass<out Animal>): List<Animal> {
        return predicateSubList { type.isInstance(it) }
    }

    /**
     * Получить подможество животных, находящихся в заданном состоянии.
     */
    fun getAnimalsWithState(state: AnimalState): List<Animal> {
        return predicateSubList { state == it.state }
    }

    /**
     * Открытие зоопарка.
     */
    fun open() {
        println("Зоопарк открывается".wrap("="))
        actAllAnimals { randomState() }
    }

    /**
     * Работа зоопарка.
     */
    fun doSomeWork() {
        for (i in 1..8) {
            println("$i-й час работы зоопарка".wrap("="))
            actAllAnimals { randomState() }
            println("Конец $i-го часа работы зоопарка".wrap("="))
            println()
        }
    }

    /**
     * Закрытие зоопарка.
     */
    override fun close() {
        println("Зоопарк закрывается".wrap("="))
        actAllAnimals { SleepingState }
    }

    fun animalsCount(): Int {
        return animals.size
    }

    /**
     * Выполнить действие для всех животных зоопарка.
     */
    private fun actAllAnimals(stateSupplier: () -> AnimalState) {
        for (animal in animals) {
            try {
                animal.state = stateSupplier.invoke()
                animal.act()
            } catch (exception: ZooDangerException) {
                println("Зоопарк в опасности! ${exception.message}".wrap("!"))
                throw exception
            } catch (exception: Exception) {
                println("Ошибка при совершении животным действия: ${exception.message}")
            }
        }
    }

    /**
     * Выборка среди всех животных зоопарка по заданному предикату.
     */
    private fun predicateSubList(filterPredicate: (Animal) -> Boolean): List<Animal> {
        return animals
            .filter(filterPredicate)
            .toList()
    }
}