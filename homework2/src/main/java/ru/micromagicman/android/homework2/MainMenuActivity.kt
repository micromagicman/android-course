package ru.micromagicman.android.homework2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.Button
import ru.micromagicman.android.homework2.domain.DynamicButtonParameter
import ru.micromagicman.android.homework2.utils.asDp
import ru.micromagicman.android.homework2.utils.asSp
import ru.micromagicman.android.homework2.utils.float
import ru.micromagicman.android.homework2.utils.int
import ru.micromagicman.android.homework2.utils.put
import ru.micromagicman.android.homework2.utils.putExtra
import ru.micromagicman.android.homework2.utils.string
import kotlin.math.roundToInt
import kotlin.reflect.KClass

/**
 * Главный экран приложения.
 */
class MainMenuActivity : Activity() {

    /**
     * Кнопка, открывающая активити, которая настраивает текст.
     */
    private lateinit var changeTextButton: Button

    /**
     * Кнопка, открывающая активити, которая настраивает отступы и размер текста.
     */
    private lateinit var changeSizesButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_menu_activity)
        changeTextButton = findViewById(R.id.button_change_text)
        changeSizesButton = findViewById(R.id.button_change_colors)

        savedInstanceState?.apply {
            string(DynamicButtonParameter.TEXT)
                ?.apply { changeTextButton.text = this }
            int(DynamicButtonParameter.PADDING)
                .apply { changeSizesButton.setPadding(this, this, this, this) }
            float(DynamicButtonParameter.TEXT_SIZE)
                .asSp(resources.displayMetrics)
                .apply { changeSizesButton.textSize = toFloat() }
        }

        registerListeners()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (RESULT_OK != resultCode) {
            super.onActivityResult(requestCode, resultCode, data)
            return
        }
        when (requestCode) {
            changeTextButton.id -> {
                changeTextButton.text = data?.string(DynamicButtonParameter.TEXT)
            }

            changeSizesButton.id -> {
                data?.let {
                    val paddingPx = TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP,
                        it.int(DynamicButtonParameter.PADDING).toFloat(),
                        resources.displayMetrics
                    ).roundToInt()
                    changeSizesButton.setPadding(
                        paddingPx,
                        paddingPx,
                        paddingPx,
                        paddingPx
                    )
                    changeSizesButton.textSize = it.int(DynamicButtonParameter.TEXT_SIZE).toFloat()
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.put(DynamicButtonParameter.TEXT, changeTextButton.text.toString())
        outState.put(DynamicButtonParameter.PADDING, changeSizesButton.paddingTop)
        outState.put(DynamicButtonParameter.TEXT_SIZE, changeSizesButton.textSize)
    }

    private fun registerListeners() {
        changeTextButton.setOnClickListener {
            startActivityForResult(
                createButtonIntent(it, ButtonTextModificationActivity::class) {
                    putExtra(DynamicButtonParameter.TEXT, changeTextButton.text.toString())
                },
                it.id
            )
        }

        changeSizesButton.setOnClickListener {
            startActivityForResult(
                createButtonIntent(it, ButtonSizesModificationActivity::class) {
                    putExtra(
                        DynamicButtonParameter.TEXT_SIZE,
                        changeSizesButton.textSize.asSp(resources.displayMetrics)
                    )
                    putExtra(
                        DynamicButtonParameter.PADDING,
                        changeSizesButton.paddingTop.asDp(resources.displayMetrics)
                    )
                },
                it.id
            )
        }
    }

    private fun createButtonIntent(
        view: View,
        target: KClass<*>,
        extras: Intent.() -> Unit
    ): Intent {
        return Intent(this, target.java)
            .putExtra("buttonId", view.id)
            .apply(extras)
    }
}