package universal.feature.basketball.scene

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import universal.feature.basketball.domain.BasketballNavigation
import universal.feature.basketball.domain.PlayerUseCase
import universal.feature.basketball.domain.PlayersUseCase
import universal.feature.basketball.model.Player
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

internal class HomeViewModel(
    private val fetchPlayers: PlayersUseCase.Fetch,
    private val displayPlayer: PlayerUseCase.Display,
    private val navigation: BasketballNavigation,
) : ViewModel() {

    private val _moviesState: MutableStateFlow<PagingData<Player>> = MutableStateFlow(value = PagingData.empty())
    val moviesState: StateFlow<PagingData<Player>> get() = _moviesState

    init {
        getPlayers()
    }

    fun onBack() {
        navigation.goBack()
    }

    private fun getPlayers() = viewModelScope.launch {
        delay(2000)
        fetchPlayers()
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect {
                _moviesState.value = it
            }
    }

    fun onPlayer(player: Player) {
        displayPlayer(player.id)
    }
}