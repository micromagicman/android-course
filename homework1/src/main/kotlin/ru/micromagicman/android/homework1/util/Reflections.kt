package ru.micromagicman.android.homework1.util

import kotlin.reflect.KClass

/**
 * Получение случайной enum-константы по заданному типу перечисления.
 */
fun <E : Enum<in E>> KClass<E>.randomConstant(): E {
    return java.enumConstants.random()
}

/**
 * Создать объект одного из класса-реализаций заданного типа с переданными параметрами.
 * Сигнатура конструкторов каждой из имплементаций должна быть одинакова.
 */
fun <T : Any> KClass<T>.newRandomImplementationInstance(vararg args: Any): T {
    val random = sealedSubclasses
        .filter { !it.isAbstract }
        .random()
    if (null != random.objectInstance) {
        return random.objectInstance!!
    }
    return random
        .constructors
        .first { constructor -> constructor.parameters.size == args.size }
        .call(*args)
}