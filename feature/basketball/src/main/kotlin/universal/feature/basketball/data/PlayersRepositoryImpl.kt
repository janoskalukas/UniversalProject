package universal.feature.basketball.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import universal.library.result.model.PageResult
import universal.feature.basketball.domain.PlayersRepository
import universal.feature.basketball.model.Player
import universal.feature.basketball.model.Players
import kotlinx.coroutines.flow.Flow

internal class PlayersRepositoryImpl(
    private val remoteResource: PlayersRemoteResource,
    private val pagingSource: MoviePagingSource,
    private val localResource: PlayersLocalResource,
) : PlayersRepository {

    override suspend fun fetchPlayers(pageNumber: Int): PageResult<Players> {
        return remoteResource.fetchPlayers(pageNumber = pageNumber)
    }

    override suspend fun getMovies(): Flow<PagingData<Player>> {
        return Pager(
            config = PagingConfig(pageSize = 25, prefetchDistance = 2),
            pagingSourceFactory = { pagingSource }
        ).flow
    }

    override suspend fun fetchPlayer(): PageResult<Player> {
        return remoteResource.fetchPlayer(playerId = localResource.loadPlayerId())
    }

    override fun storePlayerId(id: Int) {
        localResource.storePlayerId(id)
    }
}