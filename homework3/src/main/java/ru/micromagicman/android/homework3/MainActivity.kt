package ru.micromagicman.android.homework3

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.micromagicman.android.homework3.service.CalculationService
import ru.micromagicman.android.homework3.service.CalculationState
import ru.micromagicman.android.homework3.service.ViewVisibilityObserver
import ru.micromagicman.android.homework3.util.intValue
import ru.micromagicman.android.homework3.util.onTextChanged
import ru.micromagicman.android.homework3.view.CalculationFormulaView
import java.math.BigDecimal

const val STATE_KEY = "state"

/**
 * Основная activity приложения.
 * Отвечает за вычисление формулы (b * a^b) / 2.
 * Значения a и b задаются с помощью полей ввода, операция выполняется асинхронно,
 * не затрагивая основной поток приложения
 */
class MainActivity : Activity() {

    private lateinit var visibilityObservers: List<ViewVisibilityObserver>

    private lateinit var viewEditXValue: EditText

    private lateinit var viewEditYValue: EditText

    private lateinit var viewCalculateButton: Button

    private lateinit var viewCancelButton: Button

    private lateinit var viewFormula: CalculationFormulaView

    private lateinit var viewProgressBar: ProgressBar

    private lateinit var viewErrorText: TextView

    private lateinit var calculationState: CalculationState

    private var calculationJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeViews()
        registerViewListeners()
        registerViewVisibilityObservers()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_KEY, calculationState.name)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.getString(STATE_KEY)?.let {
            notifyState(CalculationState.valueOf(it))
        }
    }

    private fun initializeViews() {
        viewEditXValue = findViewById(R.id.value_x)
        viewEditYValue = findViewById(R.id.value_y)
        viewCalculateButton = findViewById(R.id.button_calculate)
        viewCancelButton = findViewById(R.id.button_cancel)
        viewFormula = findViewById(R.id.formula)
        viewProgressBar = findViewById(R.id.progress_bar)
        viewErrorText = findViewById(R.id.error_text)
    }

    private fun registerViewListeners() {
        viewEditXValue.onTextChanged {
            cancelCalculation()
            notifyState(CalculationState.INPUT)
            viewFormula.setX(viewEditXValue.intValue())
        }
        viewEditYValue.onTextChanged {
            cancelCalculation()
            notifyState(CalculationState.INPUT)
            viewFormula.setY(viewEditYValue.intValue())
        }
        viewCancelButton.setOnClickListener {
            cancelCalculation()
        }
        viewCalculateButton.setOnClickListener {
            cancelCalculation()
            notifyState(CalculationState.CALCULATION)
            calculationJob = CoroutineScope(Dispatchers.Default)
                .launch { doCalculation() }
        }
    }

    private fun registerViewVisibilityObservers() {
        visibilityObservers = listOf(
            ViewVisibilityObserver(viewCancelButton, setOf(CalculationState.CALCULATION)),
            ViewVisibilityObserver(viewProgressBar, setOf(CalculationState.CALCULATION)),
            ViewVisibilityObserver(viewFormula, setOf(CalculationState.INPUT)),
            ViewVisibilityObserver(viewErrorText, setOf(CalculationState.CALCULATION_ERROR))
        )
        notifyState(CalculationState.INPUT)
    }

    private fun cancelCalculation() {
        calculationJob?.apply {
            if (!isCompleted) {
                cancel()
                onCalculationCancelled()
            }
        }
    }

    private fun onCalculationCancelled() {
        viewFormula.reset()
        notifyState(CalculationState.INPUT)
    }

    private fun onCalculationSuccess(answer: BigDecimal) {
        viewFormula.setAnswer(answer)
        notifyState(CalculationState.INPUT)
    }

    private fun onCalculationError(error: Throwable) {
        notifyState(CalculationState.CALCULATION_ERROR)
    }

    private suspend fun doCalculation() {
        try {
            val result = CalculationService.calculate(
                viewEditXValue.intValue(),
                viewEditYValue.intValue()
            )
            withContext(Dispatchers.Main) {
                onCalculationSuccess(result)
            }
        } catch (cancellationException: CancellationException) {
            withContext(Dispatchers.Main) {
                onCalculationCancelled()
            }
        } catch (unknownError: Throwable) {
            withContext(Dispatchers.Main) {
                onCalculationError(unknownError)
            }
        }
    }

    private fun notifyState(state: CalculationState) {
        calculationState = state
        visibilityObservers.forEach { it.onChanged(calculationState) }
    }
}