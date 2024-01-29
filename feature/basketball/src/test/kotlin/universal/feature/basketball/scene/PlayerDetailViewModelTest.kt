package universal.feature.basketball.scene

import io.kotest.matchers.shouldBe
import io.mockk.coVerify
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test
import universal.feature.basketball.domain.PlayerUseCase
import universal.feature.basketball.localisationService
import universal.feature.basketball.model.Player
import universal.feature.basketball.player
import universal.feature.basketball.presentation.PlayerFormat
import universal.feature.basketball.presentation.PlayerState
import universal.feature.basketball.presentation.PositionFormat
import universal.library.architecture.fixtures.domain.coUnitMock
import universal.library.architecture.fixtures.infrastructure.CoroutinesDispatcherRule
import universal.library.architecture.fixtures.infrastructure.coTest
import universal.library.localisation.domain.LocalisationService
import universal.library.localisation.fixtures.domain.localiseTo
import universal.library.localisation.infrastructure.string
import universal.library.mvvm.fixtures.presentation.lastContent
import universal.library.result.model.PageResult

internal class PlayerDetailViewModelTest {

    @get:Rule
    val coroutinesRule = CoroutinesDispatcherRule()

    @Test
    fun `should fetch player initially`() = coTest {
        val fetchPlayer: PlayerUseCase.Fetch = coUnitMock(fetchSuccess())

        viewModel(fetchPlayer = fetchPlayer)

        coVerify { fetchPlayer() }
    }

    @Test
    fun `should map to state`() {
        val player = player()
        val fetchPlayer: PlayerUseCase.Fetch = coUnitMock(fetchSuccess(player))

        viewModel(
            fetchPlayer = fetchPlayer,
            localisationService = string.center localiseTo "center",
        )
            .lastContent()
            .player shouldBe playerState(position = "center")
    }

    private fun fetchSuccess(player: Player = player()): PageResult<Player> = PageResult.Success(player)

    private fun playerState(position: String = "center") = PlayerState(
        id = 0,
        fullName = "",
        team = "",
        teamId = 0,
        position = position,
        imageUrl = "",
        height = null,
        weight = null,
    )

    private fun viewModel(
        fetchPlayer: PlayerUseCase.Fetch = coUnitMock(fetchSuccess()),
        localisationService: LocalisationService = localisationService(),
    ) = PlayerDetailViewModel(
        fetchPlayer = fetchPlayer,
        playerFormat = PlayerFormat(localisationService, PositionFormat(localisationService)),
        teamFormat = mockk(relaxed = true),
    )
}