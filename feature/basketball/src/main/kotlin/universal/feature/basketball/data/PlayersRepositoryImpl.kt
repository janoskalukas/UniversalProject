package universal.feature.basketball.data

import universal.feature.basketball.domain.PlayersRepository
import universal.feature.basketball.model.Player
import universal.library.result.model.PageResult

internal class PlayersRepositoryImpl(
    private val remoteResource: PlayersRemoteResource,
    private val localResource: PlayersLocalResource,
) : PlayersRepository {

    override suspend fun fetchPlayers(pageNumber: Int): PageResult<List<Player>> {
        return remoteResource.fetchPlayers(pageNumber)
    }

    override suspend fun fetchPlayer(): PageResult<Player> {
        return remoteResource.fetchPlayer(id = localResource.loadPlayerId())
    }

    override fun storePlayerId(id: Int) {
        localResource.storePlayerId(id)
    }
}