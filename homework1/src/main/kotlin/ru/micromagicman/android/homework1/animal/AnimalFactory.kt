package ru.micromagicman.android.homework1.animal

/**
 * Фабрика животных.
 */
interface AnimalFactory {

    /**
     * Создание нового объекта, описывающего то или иное животное.
     */
    fun newAnimal(): Animal
}