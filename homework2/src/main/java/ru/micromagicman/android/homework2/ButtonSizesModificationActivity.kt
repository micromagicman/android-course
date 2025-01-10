package ru.micromagicman.android.homework2

import android.content.Intent
import android.os.Bundle
import ru.micromagicman.android.homework2.domain.DynamicButtonParameter
import ru.micromagicman.android.homework2.utils.int
import ru.micromagicman.android.homework2.utils.put
import ru.micromagicman.android.homework2.utils.putExtra
import ru.micromagicman.android.homework2.view.SizeView

/**
 * Активити для изменения размеров кнопки.
 */
class ButtonSizesModificationActivity
    : ButtonHandlingActivity(R.layout.button_sizes_modification_activity) {

    private lateinit var buttonTextSizeView: SizeView

    private lateinit var buttonPaddingView: SizeView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        buttonTextSizeView = findViewById(R.id.button_text_size_view)
        buttonPaddingView = findViewById(R.id.button_padding_view)

        if (null != savedInstanceState) {
            savedInstanceState.apply {
                buttonPaddingView.setValue(int(DynamicButtonParameter.PADDING))
                buttonTextSizeView.setValue(int(DynamicButtonParameter.TEXT_SIZE))
            }
        } else {
            buttonTextSizeView.setValue(intent.int(DynamicButtonParameter.TEXT_SIZE))
            buttonPaddingView.setValue(intent.int(DynamicButtonParameter.PADDING))
        }
    }

    override fun Intent.appendExtras() {
        putExtra(DynamicButtonParameter.TEXT_SIZE, buttonTextSizeView.value())
        putExtra(DynamicButtonParameter.PADDING, buttonPaddingView.value())
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.put(DynamicButtonParameter.TEXT_SIZE, buttonTextSizeView.value())
        outState.put(DynamicButtonParameter.PADDING, buttonPaddingView.value())
        super.onSaveInstanceState(outState)
    }
}