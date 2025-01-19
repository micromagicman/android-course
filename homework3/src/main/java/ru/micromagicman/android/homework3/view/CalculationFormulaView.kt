package ru.micromagicman.android.homework3.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.graphics.Path
import android.graphics.Typeface
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View
import ru.micromagicman.android.homework3.R
import ru.micromagicman.android.homework3.util.MeasuredText
import java.math.BigDecimal
import kotlin.math.roundToInt

const val DEFAULT_DENOMINATOR = "1"
const val PARENT_STATE_KEY = "parent"
const val ANSWER_STATE_KEY = "answer"

val MULTIPLY_SIGN_PAINT = Paint(ANTI_ALIAS_FLAG)
    .apply {
        style = Paint.Style.FILL
        color = Color.BLACK
    }

val PATH = Path()

var EQUALITY_STRING = " ="

/**
 * Кастомная view, отображающая формулу (y * x^y) / 2 в читаемом виде.
 */
class CalculationFormulaView(context: Context, attributes: AttributeSet) :
    View(context, attributes) {

    private var x: MeasuredText

    private var y: MeasuredText

    private var denominator: MeasuredText

    private var answer: MeasuredText? = null

    /**
     * Настройки рендеринга текста.
     */
    private var textPaint = Paint(ANTI_ALIAS_FLAG)
        .apply {
            typeface = Typeface.MONOSPACE
            textSize = context.resources.displayMetrics.density * 16f
            color = Color.BLACK
        }

    /**
     * Настройки рендеринга примитивов формулы (например, черты дроби).
     */
    private var fractionPaint = Paint(ANTI_ALIAS_FLAG)
        .apply {
            style = Paint.Style.STROKE
            color = Color.BLACK
            strokeWidth = context.resources.displayMetrics.density * 2f
        }

    init {
        context.theme
            .obtainStyledAttributes(attributes, R.styleable.CalculationFormulaView, 0, 0)
            .apply {
                x = MeasuredText(getString(R.styleable.CalculationFormulaView_x) ?: "", textPaint)
                y = MeasuredText(getString(R.styleable.CalculationFormulaView_y) ?: "", textPaint)
                denominator = MeasuredText(
                    getString(R.styleable.CalculationFormulaView_denominator)
                        ?: DEFAULT_DENOMINATOR,
                    textPaint
                )
            }
    }

    fun setX(newX: Int) {
        val bigDecimalAsString = newX.toString()
        if (bigDecimalAsString != x.text) {
            x = MeasuredText(bigDecimalAsString, textPaint)
            reset()
        }
    }

    fun setY(newY: Int) {
        val yAsString = newY.toString()
        if (yAsString != y.text) {
            y = MeasuredText(yAsString, textPaint)
            reset()
        }
    }

    fun setAnswer(answer: BigDecimal) {
        setAnswerString("$EQUALITY_STRING ${answer.toEngineeringString()}")
    }

    fun reset() {
        answer = null
        requestLayout()
    }

    private fun setAnswerString(answerAsString: String) {
        answer = MeasuredText(answerAsString, textPaint)
        requestLayout()
    }

    override fun onSaveInstanceState(): Parcelable {
        return Bundle().apply {
            putParcelable(PARENT_STATE_KEY, super.onSaveInstanceState())
            putString(ANSWER_STATE_KEY, answer?.text)
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        if (state is Bundle) {
            state.getString(ANSWER_STATE_KEY)?.let {
                setAnswerString(it)
            }
            super.onRestoreInstanceState(state.getParcelable(PARENT_STATE_KEY))
            requestLayout()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val xValueWidth = x.intWidthPx()
        val yValueWidth = y.intWidthPx()
        setMeasuredDimension(
            xValueWidth + 2 * yValueWidth + 20 + (answer?.intWidthPx() ?: 0),
            (textPaint.textSize * 3).roundToInt()
        )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        PATH.reset()
        val firstLineBottomY = textPaint.textSize * 1.6f

        x.render(canvas, 0f, firstLineBottomY)
        y.render(canvas, x.widthPx, firstLineBottomY - textPaint.textSize * .6f)

        val degree = x.widthPx + y.widthPx
        canvas.drawCircle(
            degree + 10,
            firstLineBottomY - (textPaint.textSize / 3),
            3f,
            MULTIPLY_SIGN_PAINT
        )

        val secondYPosition = degree + 23
        y.render(canvas, secondYPosition, firstLineBottomY)

        val fractionWidthPx = secondYPosition + y.widthPx
        canvas.drawPath(
            PATH.apply {
                val attribute = firstLineBottomY + fractionPaint.strokeWidth * 2
                moveTo(0f, attribute)
                lineTo(fractionWidthPx, attribute)
            },
            fractionPaint
        )

        denominator.render(
            canvas,
            (fractionWidthPx / 2 - denominator.widthPx / 2),
            height.toFloat()
        )

        answer?.render(canvas, fractionWidthPx, firstLineBottomY * 1.4f)
    }
}