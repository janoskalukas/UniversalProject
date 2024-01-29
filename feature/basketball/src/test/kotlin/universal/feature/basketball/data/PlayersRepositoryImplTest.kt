package universal.feature.basketball.data

import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.beInstanceOf
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Test
import universal.feature.basketball.model.Player
import universal.feature.basketball.player
import universal.library.architecture.fixtures.infrastructure.coTest
import universal.library.result.model.PageResult

internal class PlayersRepositoryImplTest {

    @Test
    fun `should return success when fetch player result succeeded`() = coTest {
        val remoteResource = returns(playerResult = fetchPlayerSuccess())
        val repository = repository(remoteResource = remoteResource)

        val result = repository.fetchPlayer()

        result should beInstanceOf<PageResult.Success<*>>()
    }

    @Test
    fun `should return failure when fetch player result failed`() = coTest {
        val remoteResource = returns(playerResult = failure())
        val repository = repository(remoteResource = remoteResource)

        val result = repository.fetchPlayer()

        result should beInstanceOf<PageResult.Failure>()
    }

    @Test
    fun `should map player result as success`() = coTest {
        val player = player(id = 545)
        val remoteResource = returns(playerResult = fetchPlayerSuccess(player))
        val repository = repository(remoteResource = remoteResource)

        val result = repository.fetchPlayer()

        result shouldBe PageResult.Success(player)
    }

    @Test
    fun `should return success when fetch players result succeeded`() = coTest {
        val remoteResource = returns(playersResult = fetchPlayersSuccess())
        val repository = repository(remoteResource = remoteResource)

        val result = repository.fetchPlayers(1)

        result should beInstanceOf<PageResult.Success<*>>()
    }

    @Test
    fun `should return failure when fetch players result failed`() = coTest {
        val remoteResource = returns(playersResult = failure())
        val repository = repository(remoteResource = remoteResource)

        val result = repository.fetchPlayers(1)

        result should beInstanceOf<PageResult.Failure>()
    }

    @Test
    fun `should map players result as success`() = coTest {
        val players = listOf(player(id = 545), player(id = 454))
        val remoteResource = returns(playersResult = fetchPlayersSuccess(players))
        val repository = repository(remoteResource = remoteResource)

        val result = repository.fetchPlayers(1)

        result shouldBe PageResult.Success(players)
    }

    @Test
    fun `should `() = coTest {
        val localResource = PlayersLocalResource()
        val repository = repository(localResource = localResource)

        repository.storePlayerId(544)

        localResource.loadPlayerId() shouldBe 544
    }

    private fun fetchPlayerSuccess(player: Player = player()) = PageResult.Success(player)
    private fun fetchPlayersSuccess(players: List<Player> = listOf(player())) = PageResult.Success(players)
    private fun failure() = PageResult.Failure

    private fun returns(
        playerResult: PageResult<Player> = fetchPlayerSuccess(),
        playersResult: PageResult<List<Player>> = fetchPlayersSuccess(),
    ): PlayersRemoteResource = mockk {
        coEvery { fetchPlayer(any()) } returns playerResult
        coEvery { fetchPlayers(any()) } returns playersResult
    }

    private fun repository(
        localResource: PlayersLocalResource = mockk(relaxed = true),
        remoteResource: PlayersRemoteResource = mockk(relaxed = true),
    ) = PlayersRepositoryImpl(
        localResource = localResource,
        remoteResource = remoteResource,
    )
}