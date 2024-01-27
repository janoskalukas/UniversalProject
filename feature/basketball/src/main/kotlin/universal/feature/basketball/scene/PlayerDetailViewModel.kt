package universal.feature.basketball.scene

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import universal.feature.basketball.domain.BasketballNavigation
import universal.feature.basketball.domain.PlayerUseCase
import universal.feature.basketball.model.Player
import universal.feature.basketball.scene.PlayerDetailViewModel.Content
import universal.library.mvvm.presentation.Lce
import universal.library.mvvm.presentation.StatefulLceViewModel
import universal.library.mvvm.presentation.ViewModelContent
import universal.library.result.model.PageResult

internal class PlayerDetailViewModel(
    private val fetchPlayer: PlayerUseCase.Fetch,
    private val navigation: BasketballNavigation,
) : StatefulLceViewModel<Content>() {

    init {
        fetch()
    }

    fun onBack() = navigation.goBack()

    fun onRetry() = fetch()

    private fun fetch() = viewModelScope.launch {
        state = Lce.Loading()
        delay(2000)
        when (val result = fetchPlayer()) {
            is PageResult.Success -> content = result.value.toContent()
            is PageResult.Failure -> state = Lce.Error
        }
    }

    private fun Player.toContent(): Content {
        return Content(name = firstName)
    }

    data class Content(
        val name: String = "",
    ) : ViewModelContent
}