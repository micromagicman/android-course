package ru.micromagicman.android.homework2.utils

import android.util.DisplayMetrics
import kotlin.math.roundToInt

fun Int.asDp(metrics: DisplayMetrics) : Int = (this / metrics.density).roundToInt()

fun Float.asSp(metrics: DisplayMetrics) : Int = (this / metrics.scaledDensity).roundToInt()