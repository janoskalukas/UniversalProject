package universal.feature.basketball.scene

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import universal.feature.basketball.domain.BasketballNavigation
import universal.feature.basketball.domain.PlayerUseCase
import universal.feature.basketball.scene.PlayerDetailViewModel.State
import universal.library.mvvm.presentation.StatefulViewModel
import universal.library.mvvm.presentation.ViewModelState
import universal.library.result.model.PageResult

internal class PlayerDetailViewModel(
    private val fetchPlayer: PlayerUseCase.Fetch,
    private val navigation: BasketballNavigation,
) : StatefulViewModel<State>(State()) {

    init {
        fetch()
    }

    fun onBack() = navigation.goBack()

    private fun fetch() = viewModelScope.launch {
        when (val result = fetchPlayer()) {
            is PageResult.Success -> state = state.copy(name = result.value.firstName)
            is PageResult.Failure -> Unit
        }
    }

    data class State(
        val name: String = "",
    ) : ViewModelState
}