package ru.micromagicman.android.homework2

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import ru.micromagicman.android.homework2.domain.DynamicButtonParameter
import ru.micromagicman.android.homework2.utils.putExtra
import ru.micromagicman.android.homework2.utils.string

/**
 * Активити для изменения текста кнопки.
 */
class ButtonTextModificationActivity
    : ButtonHandlingActivity(R.layout.button_text_modification_activity) {

    /**
     * Поле для ввода текста на кнопке.
     */
    private lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        editText = findViewById(R.id.button_text)

        intent.string(DynamicButtonParameter.TEXT)
            ?.apply { editText.setText(this) }
    }

    override fun Intent.appendExtras() {
        putExtra(DynamicButtonParameter.TEXT, editText.text.toString())
    }
}