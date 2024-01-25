package universal.feature.basketball.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import universal.feature.basketball.model.Player
import universal.library.result.model.PageResult

internal interface PlayersRepository {

    suspend fun fetchPlayers(): Flow<PagingData<Player>>
    suspend fun fetchPlayer(): PageResult<Player>
    fun storePlayerId(id: Int)
}