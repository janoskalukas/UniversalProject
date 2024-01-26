package universal.feature.basketball.scene

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import universal.feature.basketball.domain.BasketballNavigation
import universal.feature.basketball.domain.PlayerUseCase
import universal.feature.basketball.domain.PlayersUseCase
import universal.feature.basketball.presentation.PlayerFormat
import universal.feature.basketball.presentation.PlayerState

internal class PlayersListViewModel(
    private val fetchPlayers: PlayersUseCase.Fetch,
    private val displayPlayer: PlayerUseCase.Display,
    private val navigation: BasketballNavigation,
    private val playerFormat: PlayerFormat,
) : ViewModel() {

    private val _playersState: MutableStateFlow<PagingData<PlayerState>> = MutableStateFlow(value = PagingData.empty())
    val playersState: StateFlow<PagingData<PlayerState>> get() = _playersState

    init {
        fetch()
    }

    fun onBack() {
        navigation.goBack()
    }

    fun onPlayer(player: PlayerState) {
        displayPlayer(player.id)
    }

    private fun fetch() = viewModelScope.launch {
        fetchPlayers()
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect {
                _playersState.value = it.map(playerFormat::format)
            }
    }
}