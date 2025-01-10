package ru.micromagicman.android.homework2.utils

import android.os.Bundle

fun Bundle.put(parameter: Enum<*>, value: String) = putString(parameter.name, value)

fun Bundle.put(parameter: Enum<*>, value: Float) = putFloat(parameter.name, value)

fun Bundle.put(parameter: Enum<*>, value: Int) = putInt(parameter.name, value)

fun Bundle.int(parameter: Enum<*>): Int = getInt(parameter.name)

fun Bundle.float(parameter: Enum<*>): Float = getFloat(parameter.name)

fun Bundle.string(parameter: Enum<*>): String? = getString(parameter.name)