package ru.micromagicman.android.homework1.util

/**
 * Обернуть строку в рамочку из заданного символа (сверху и снизу)
 */
fun String.wrap(symbol: String): String {
    val wrapper = symbol.repeat(length)
    return "$wrapper\n$this\n$wrapper"
}