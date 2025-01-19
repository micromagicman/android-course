package ru.micromagicman.android.homework3.service

/**
 * Состояние вычисления выражения.
 */
enum class CalculationState {

    /**
     * Ввод данных.
     */
    INPUT,

    /**
     * Вычисление.
     */
    CALCULATION,

    /**
     * Ошибка при вычислении выражения.
     */
    CALCULATION_ERROR
}
