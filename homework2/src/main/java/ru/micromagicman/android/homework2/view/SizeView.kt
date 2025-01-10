package ru.micromagicman.android.homework2.view

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import ru.micromagicman.android.homework2.R
import kotlin.math.roundToInt

/**
 * View, отвечающая за конфигурацию диапазона некоторого значения.
 * В данном приложении используется для настройки отступов и размера текста у кнопки.
 */
class SizeView(context: Context, attributes: AttributeSet) : LinearLayout(context, attributes),
    OnSeekBarChangeListener {

    /**
     * TextView для отображения текущего значения размера.
     */
    private val sizeTextView: TextView

    /**
     * View для настройки самого значения размера.
     */
    private val mRange: SeekBar

    /**
     * Минимально возможное значение для диапазона.
     */
    private val minValue: Int

    /**
     * Максимально возможное значение для диапазона.
     */
    private val maxValue: Int

    init {
        orientation = VERTICAL
        sizeTextView = TextView(context)
        val labelTextView = TextView(context)

        addView(
            LinearLayout(context)
                .apply {
                    orientation = HORIZONTAL
                    addView(labelTextView)
                    addView(
                        TextView(context).apply {
                            val marginHorizontal = context.resources.getDimension(R.dimen.default_horizontal_margin)
                            layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                                .apply {
                                    marginStart = marginHorizontal.roundToInt()
                                    marginEnd = marginHorizontal.roundToInt()
                                }
                            text = context.resources.getString(R.string.dash)
                        }
                    )
                    addView(sizeTextView)
                }
        )

        mRange = SeekBar(context)
        mRange.setOnSeekBarChangeListener(this)
        addView(mRange)

        context.theme
            .obtainStyledAttributes(attributes, R.styleable.SizeView, 0, 0)
            .apply {
                minValue = getInt(R.styleable.SizeView_min, 0)
                maxValue = getInt(R.styleable.SizeView_max, 30)
                labelTextView.text = getString(R.styleable.SizeView_label)
                    ?: context.resources.getString(R.string.size)
                mRange.max = maxValue - minValue
            }
    }

    fun value(): Int {
        return mRange.progress + minValue;
    }

    fun setValue(value: Int) {
        mRange.progress = value - minValue
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        updateLabel(progress)
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {
        // do nothing
    }

    override fun onStopTrackingTouch(seekBar: SeekBar?) {
        // do nothing
    }

    private fun updateLabel(progress: Int) {
        val displayValue = minValue + progress
        sizeTextView.text = "$displayValue"
    }
}