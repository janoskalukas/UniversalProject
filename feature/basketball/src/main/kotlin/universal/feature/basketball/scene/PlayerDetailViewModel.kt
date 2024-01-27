package universal.feature.basketball.scene

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import universal.feature.basketball.domain.PlayerUseCase
import universal.feature.basketball.model.Player
import universal.feature.basketball.presentation.PlayerFormat
import universal.feature.basketball.presentation.PlayerState
import universal.feature.basketball.presentation.TeamFormat
import universal.feature.basketball.presentation.TeamState
import universal.feature.basketball.scene.PlayerDetailViewModel.Content
import universal.library.mvvm.presentation.Lce
import universal.library.mvvm.presentation.StatefulLceViewModel
import universal.library.mvvm.presentation.ViewModelContent
import universal.library.result.model.PageResult

internal class PlayerDetailViewModel(
    private val fetchPlayer: PlayerUseCase.Fetch,
    private val playerFormat: PlayerFormat,
    private val teamFormat: TeamFormat,
) : StatefulLceViewModel<Content>() {

    init {
        fetch()
    }

    fun onRetry() = fetch()

    private fun fetch() = viewModelScope.launch {
        state = Lce.Loading()
        when (val result = fetchPlayer()) {
            is PageResult.Success -> content = result.value.let(::toContent)
            is PageResult.Failure -> state = Lce.Error
        }
    }

    private fun toContent(player: Player): Content {
        return Content(
            player = player.let(playerFormat::format),
            team = player.team.let(teamFormat::format),
        )
    }

    data class Content(
        val player: PlayerState,
        val team: TeamState,
    ) : ViewModelContent
}