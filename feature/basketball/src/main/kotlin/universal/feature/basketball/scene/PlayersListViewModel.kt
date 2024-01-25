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
import universal.feature.basketball.model.Player

internal class PlayersListViewModel(
    private val fetchPlayers: PlayersUseCase.Fetch,
    private val displayPlayer: PlayerUseCase.Display,
    private val navigation: BasketballNavigation,
) : ViewModel() {

    private val _playersState: MutableStateFlow<PagingData<PlayerState>> = MutableStateFlow(value = PagingData.empty())
    val playersState: StateFlow<PagingData<PlayerState>> get() = _playersState

    init {
        fetch()
    }

    fun onBack() {
        navigation.goBack()
    }

    private fun fetch() = viewModelScope.launch {
        fetchPlayers()
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect {
                _playersState.value = it.map(::toState)
            }
    }

    private fun toState(player: Player): PlayerState = with(player) {
        PlayerState(
            id = id,
            fullName = "$firstName $lastName",
            description = "${team.city} ${team.name}",
            imageUrl = imageUrl,
        )
    }

    fun onPlayer(player: PlayerState) {
        displayPlayer(player.id)
    }

    data class PlayerState(
        val id: Int,
        val fullName: String,
        val description: String,
        val imageUrl: String,
    )
}