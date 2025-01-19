package ru.micromagicman.android.homework3.util

import android.graphics.Canvas
import android.graphics.Paint
import kotlin.math.roundToInt

class MeasuredText(var text: String, private var targetPaint: Paint) {

    val widthPx: Float = targetPaint.measureText(text)

    fun intWidthPx(): Int = widthPx.roundToInt()

    fun render(canvas: Canvas, x: Float, firstLineBottomY: Float) {
        canvas.drawText(text, x, firstLineBottomY, targetPaint)
    }
}