package ru.micromagicman.android.homework3.service

import kotlinx.coroutines.delay
import java.math.BigDecimal
import java.util.concurrent.ConcurrentHashMap

/**
 * Сервис, задачей которого является вычисление формулы (y * x^y) / 2.
 */
object CalculationService {

    /**
     * Значение в знаменателе
     */
    private val denominator = BigDecimal(2)

    /**
     * In-memory кэш, содержащий результат вычисления выражения
     * по заданной паре x, y
     */
    private val cache = ConcurrentHashMap<Pair<Int, Int>, BigDecimal>()

    /**
     * Вычисление выражения.
     * В случае отстутсвия ответа в кэше, делается задержка для возможности отмены вычисления.
     */
    suspend fun calculate(a: Int, b: Int): BigDecimal {
        val key = Pair(a, b)
        if (cache.containsKey(key)) {
            return cache[key] ?: BigDecimal.ZERO
        }
        delay(2000L)
        return cache.computeIfAbsent(key) { k ->
            k.run {
                (BigDecimal(first).pow(second) * BigDecimal(second)) / denominator
            }
        }
    }
}