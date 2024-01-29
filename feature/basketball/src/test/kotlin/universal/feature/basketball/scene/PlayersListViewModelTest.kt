package universal.feature.basketball.scene

import io.mockk.verify
import org.junit.Rule
import org.junit.Test
import universal.feature.basketball.domain.PlayerUseCase
import universal.feature.basketball.domain.PlayersUseCase
import universal.feature.basketball.localisationService
import universal.feature.basketball.model.Player
import universal.feature.basketball.presentation.PlayerListItemFormat
import universal.feature.basketball.presentation.PlayerListItemState
import universal.feature.basketball.presentation.PositionFormat
import universal.library.architecture.fixtures.domain.coMock
import universal.library.architecture.fixtures.domain.mock
import universal.library.architecture.fixtures.infrastructure.CoroutinesDispatcherRule
import universal.library.architecture.fixtures.infrastructure.coTest
import universal.library.localisation.domain.LocalisationService
import universal.library.result.model.PageResult

internal class PlayersListViewModelTest {

    @get:Rule
    val coroutinesRule = CoroutinesDispatcherRule()

    @Test
    fun `should display player on display player`() = coTest {
        val displayPlayer: PlayerUseCase.Display = mock()

        viewModel(displayPlayer = displayPlayer).onPlayer(playerListItemState(123))

        verify { displayPlayer(123) }
    }

    private fun playerListItemState(id: Int) = PlayerListItemState(
        id = id,
        fullName = "full name",
        team = "team",
        position = "center",
        imageUrl = "url",
    )

    private fun fetchSuccess(players: List<Player> = listOf()): PageResult<List<Player>> = PageResult.Success(players)

    private fun viewModel(
        fetchPlayers: PlayersUseCase.Fetch = coMock(fetchSuccess()),
        displayPlayer: PlayerUseCase.Display = mock(),
        localisationService: LocalisationService = localisationService(),
    ) = PlayersListViewModel(
        fetchPlayers = fetchPlayers,
        displayPlayer = displayPlayer,
        playerListItemFormat = PlayerListItemFormat(
            localisationService = localisationService,
            positionFormat = PositionFormat(localisationService = localisationService),
        ),
    )
}