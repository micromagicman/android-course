package ru.micromagicman.android.homework2

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button

/**
 * Абстрактная активити, описывающая интерфейс для настройи параметров кнопки.
 * Обработчик нажатия кнопки, запускающий данную активити передает через Intent идентификатор кнопки
 * и набор конфигурируемых параметров. В ответе актитвити должна вернуть тот же идентификатор кнопки с
 * измененными (в зависимости от действия пользователя на данной активити) параметрами.
 */
sealed class ButtonHandlingActivity(private val layoutResource: Int) : Activity() {

    private lateinit var buttonBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResource)

        buttonBack = findViewById(R.id.button_back)
        buttonBack.setOnClickListener { onBackPressed() }
    }

    override fun onBackPressed() {
        setResult(
            RESULT_OK,
            Intent(this, MainMenuActivity::class.java)
                .apply { appendExtras() }
        )
        finish()
    }

    /**
     * Положить новые значения параметров для кнопки в Intent-ответ для запрашиваемой ответ активити.
     */
    protected abstract fun Intent.appendExtras()
}