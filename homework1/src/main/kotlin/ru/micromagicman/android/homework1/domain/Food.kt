package ru.micromagicman.android.homework1.domain

/**
 * Тип еды, доступной животному для поедания.
 */
enum class Food(private val textValue: String) {

    MEAT("Мясо"),

    VEGETABLES("Овощи"),

    FRUITS("Фрукты");

    override fun toString(): String {
        return textValue
    }
}