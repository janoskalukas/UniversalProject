package universal.feature.basketball.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import universal.feature.basketball.domain.PlayersRepository
import universal.feature.basketball.model.Player
import universal.library.result.model.PageResult

internal class PlayersRepositoryImpl(
    private val remoteResource: PlayersRemoteResource,
    private val pagingSource: BasketballPagingSource,
    private val localResource: PlayersLocalResource,
) : PlayersRepository {

    override suspend fun fetchPlayers(): Flow<PagingData<Player>> {
        return Pager(
            config = PagingConfig(pageSize = 35, prefetchDistance = 2),
            pagingSourceFactory = { pagingSource },
        ).flow
    }

    override suspend fun fetchPlayer(): PageResult<Player> {
        return remoteResource.fetchPlayer(playerId = localResource.loadPlayerId())
    }

    override fun storePlayerId(id: Int) {
        localResource.storePlayerId(id)
    }
}