package universal.feature.basketball.domain

import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import junitparams.JUnitParamsRunner
import junitparams.Parameters
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import universal.feature.basketball.model.Player
import universal.feature.basketball.player
import universal.library.result.model.PageResult

@RunWith(JUnitParamsRunner::class)
internal class PlayersUseCaseTest {

    @Test
    @Parameters(method = "fetch players results")
    fun `should return result of players fetch`(fetchResult: PageResult<List<Player>>): Unit = runTest {
        val fetch = PlayersUseCase.Fetch(repository = mockk { coEvery { fetchPlayers(any()) } returns fetchResult })

        val result = fetch(1)

        result shouldBe fetchResult
    }

    @Suppress("unused")
    private fun `fetch players results`(): Any = setOf(
        PageResult.Success(listOf(player(id = 1), player(id = 2))), PageResult.Failure,
    )
}