package universal.library.mvvm.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

/**
 * ViewModel that contains an instance of [ViewModelState].
 * State is always available, observable and conflated as [StateFlow].
 */
public abstract class StatefulViewModel<S : ViewModelState>(initialState: S) : ViewModel() {

    private val statesFlow = MutableStateFlow(initialState)

    protected var state: S
        get() = statesFlow.value
        set(value) {
            statesFlow.value = value
        }

    public val states: StateFlow<S> = statesFlow
}