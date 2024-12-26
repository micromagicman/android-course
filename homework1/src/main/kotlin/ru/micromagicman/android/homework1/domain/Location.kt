package ru.micromagicman.android.homework1.domain;

/**
 * Место обитания.
 */
enum class Location(private val textValue: String) {

    TREE("Дерево"),

    GARDEN("Сад"),

    HOUSE("Дом"),

    FOREST("Лес"),

    SAVANNAH("Саванна");

    override fun toString(): String {
        return textValue
    }
}
