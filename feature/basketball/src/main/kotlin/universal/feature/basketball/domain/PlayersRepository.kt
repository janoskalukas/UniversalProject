package universal.feature.basketball.domain

import androidx.paging.PagingData
import universal.library.result.model.PageResult
import universal.feature.basketball.model.Player
import universal.feature.basketball.model.Players
import kotlinx.coroutines.flow.Flow

internal interface PlayersRepository {

    suspend fun fetchPlayers(pageNumber: Int): PageResult<Players>

    suspend fun getMovies(): Flow<PagingData<Player>>

    suspend fun fetchPlayer(): PageResult<Player>
    fun storePlayerId(id: Int)
}