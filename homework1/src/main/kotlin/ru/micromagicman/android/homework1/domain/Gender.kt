package ru.micromagicman.android.homework1.domain

/**
 * Перечисление полов со списком доступных имен для каждого из них.
 */
enum class Gender(private val humanReadableName: String, private val availableNames: List<String>) {

    /**
     * Самец.
     */
    MALE(
        "Самец",
        listOf(
            "Рудольф",
            "Том",
            "Джерри"
        )
    ),

    /**
     * Самка.
     */
    FEMALE(
        "Самка",
        listOf(
            "Инди",
            "Рози",
            "Бонитта",
            "Джессика"
        )
    );

    /**
     * Случайное имя для данного пола.
     */
    fun randomName(): String {
        return availableNames.random()
    }

    override fun toString(): String {
        return humanReadableName
    }
}