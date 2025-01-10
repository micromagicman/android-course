package ru.micromagicman.android.homework2.utils

import android.content.Intent

fun Intent.putExtra(parameter: Enum<*>, value: String): Intent = putExtra(parameter.name, value)

fun Intent.putExtra(parameter: Enum<*>, value: Float): Intent = putExtra(parameter.name, value)

fun Intent.putExtra(parameter: Enum<*>, value: Int): Intent = putExtra(parameter.name, value)

fun Intent.int(parameter: Enum<*>): Int = getIntExtra(parameter.name, 0)

fun Intent.float(parameter: Enum<*>): Float = getFloatExtra(parameter.name, 0.0f)

fun Intent.string(parameter: Enum<*>): String? = getStringExtra(parameter.name)