package universal.feature.basketball.domain

import universal.feature.basketball.model.Player
import universal.library.result.model.PageResult

internal interface PlayersRepository {

    suspend fun fetchPlayers(pageNumber: Int): PageResult<List<Player>>
    suspend fun fetchPlayer(): PageResult<Player>
    fun storePlayerId(id: Int)
}