package universal.feature.basketball.scene

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import universal.feature.basketball.domain.PlayerUseCase
import universal.feature.basketball.domain.PlayersUseCase
import universal.feature.basketball.presentation.PlayerListItemFormat
import universal.feature.basketball.presentation.PlayerListItemState
import universal.library.paging.model.PagingResult
import universal.library.paging.system.createPager
import universal.library.result.model.PageResult

internal class PlayersListViewModel(
    private val fetchPlayers: PlayersUseCase.Fetch,
    private val displayPlayer: PlayerUseCase.Display,
    private val playerListItemFormat: PlayerListItemFormat,
) : ViewModel() {

    val playersState: Flow<PagingData<PlayerListItemState>> = createPager(block = { onLoadPlayers(it) })
        .flow
        .cachedIn(viewModelScope)

    private suspend fun onLoadPlayers(pageNumber: Int): PagingResult<PlayerListItemState> {
        return when (val result = fetchPlayers(pageNumber)) {
            is PageResult.Success -> PagingResult.Success(result.value.map(playerListItemFormat::format))
            is PageResult.Failure -> PagingResult.Failure
        }
    }

    fun onPlayer(player: PlayerListItemState) = displayPlayer(player.id)
}