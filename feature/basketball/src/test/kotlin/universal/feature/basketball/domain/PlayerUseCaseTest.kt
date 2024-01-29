package universal.feature.basketball.domain

import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verifySequence
import junitparams.JUnitParamsRunner
import junitparams.Parameters
import org.junit.Test
import org.junit.runner.RunWith
import universal.feature.basketball.model.Player
import universal.feature.basketball.navigation
import universal.feature.basketball.player
import universal.library.architecture.fixtures.infrastructure.coTest
import universal.library.result.model.PageResult

@RunWith(JUnitParamsRunner::class)
internal class PlayerUseCaseTest {

    @Test
    fun `should store player id and navigate to detail`() {
        val playerId = 1
        val navigation = navigation()
        val repository: PlayersRepository = mockk { coEvery { storePlayerId(any()) } returns Unit }
        val displayPlayer = PlayerUseCase.Display(repository = repository, navigation = navigation)

        displayPlayer(playerId)

        verifySequence {
            repository.storePlayerId(playerId)
            navigation.goToPlayerDetail()
        }
    }

    @Test
    @Parameters(method = "fetch detail results")
    fun `should return result of player detail fetch`(fetchResult: PageResult<Player>): Unit = coTest {
        val fetch = PlayerUseCase.Fetch(
            repository = mockk { coEvery { fetchPlayer() } returns fetchResult },
        )

        val result = fetch()

        result shouldBe fetchResult
    }

    @Suppress("unused")
    private fun `fetch detail results`(): Any = setOf(
        PageResult.Success(player()), PageResult.Failure,
    )
}