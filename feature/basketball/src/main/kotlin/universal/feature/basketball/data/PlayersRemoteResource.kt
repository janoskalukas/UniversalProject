package universal.feature.basketball.data

import universal.feature.basketball.model.Player
import universal.library.result.data.of
import universal.library.result.model.PageResult
import universal.networking.basketball.BasketballApi

internal class PlayersRemoteResource(
    private val api: BasketballApi,
) {

    suspend fun fetchPlayer(playerId: Int): PageResult<Player> = PageResult.of {
        api
            .getPlayer(playerId = playerId)
            .let(PlayerConverter::toDomain)
    }
}