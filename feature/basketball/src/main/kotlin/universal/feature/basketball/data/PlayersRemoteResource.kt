package universal.feature.basketball.data

import universal.library.result.data.of
import universal.library.result.model.PageResult
import universal.feature.basketball.model.Player
import universal.feature.basketball.model.Players
import universal.networking.basketball.BasketballApi

class PlayersRemoteResource(
    private val api: BasketballApi,
) {

    suspend fun fetchPlayers(
        pageNumber: Int
    ): PageResult<Players> = PageResult.of {
        api
            .getPlayers(pageNumber = pageNumber, perPage = 10)
            .let(PlayersConverter::toDomain)
    }

    suspend fun fetchPlayer(playerId: Int): PageResult<Player> = PageResult.of {
        api
            .getPlayer(playerId = playerId)
            .let(PlayerConverter::toDomain)
    }
}