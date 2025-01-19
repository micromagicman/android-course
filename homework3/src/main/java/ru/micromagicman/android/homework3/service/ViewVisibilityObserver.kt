package ru.micromagicman.android.homework3.service

import android.arch.lifecycle.Observer
import android.view.View
import ru.micromagicman.android.homework3.util.hide
import ru.micromagicman.android.homework3.util.show

/**
 * Observer, делающий элемент интерфейса (не)видимым в зависимости от состояния вычисления.
 */
class ViewVisibilityObserver(
    private val target: View,
    private val visibleStates: Set<CalculationState>
) : Observer<CalculationState> {

    override fun onChanged(state: CalculationState?) {
        state?.let {
            if (visibleStates.contains(it)) target.show() else target.hide()
        } ?: run {
            target.hide()
        }
    }
}