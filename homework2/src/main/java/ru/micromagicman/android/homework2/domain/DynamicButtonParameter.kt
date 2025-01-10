package ru.micromagicman.android.homework2.domain

/**
 * Параметры кнопок, которые можно задавать динамически
 */
enum class DynamicButtonParameter {

    /**
     * Текст на кнопке.
     */
    TEXT,

    /**
     * Размер текста.
     */
    TEXT_SIZE,

    /**
     * Отступы внутри кнопки (между границей и текстом).
     */
    PADDING;
}