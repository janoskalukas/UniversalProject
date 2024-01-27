package universal.feature.basketball.scene

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import universal.feature.basketball.domain.BasketballNavigation
import universal.feature.basketball.domain.PlayerUseCase
import universal.feature.basketball.model.Player
import universal.feature.basketball.presentation.PlayerDetailFormat
import universal.feature.basketball.presentation.PlayerDetailState
import universal.feature.basketball.presentation.PlayerFormat
import universal.feature.basketball.presentation.PlayerState
import universal.feature.basketball.scene.PlayerDetailViewModel.Content
import universal.library.mvvm.presentation.Lce
import universal.library.mvvm.presentation.StatefulLceViewModel
import universal.library.mvvm.presentation.ViewModelContent
import universal.library.result.model.PageResult

internal class PlayerDetailViewModel(
    private val fetchPlayer: PlayerUseCase.Fetch,
    private val navigation: BasketballNavigation,
    private val playerFormat: PlayerDetailFormat,
) : StatefulLceViewModel<Content>() {

    init {
        fetch()
    }

    fun onBack() = navigation.goBack()

    fun onRetry() = fetch()

    private fun fetch() = viewModelScope.launch {
        state = Lce.Loading()
        when (val result = fetchPlayer()) {
            is PageResult.Success -> content = result.value.let(::toContent)
            is PageResult.Failure -> state = Lce.Error
        }
    }

    private fun toContent(player: Player): Content {
        return Content(player = player.let(playerFormat::format))
    }

    data class Content(
        val player: PlayerDetailState,
    ) : ViewModelContent
}