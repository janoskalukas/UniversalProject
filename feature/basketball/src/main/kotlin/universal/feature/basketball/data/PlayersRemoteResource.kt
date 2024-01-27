package universal.feature.basketball.data

import universal.feature.basketball.model.Player
import universal.library.result.data.of
import universal.library.result.model.PageResult
import universal.networking.basketball.BasketballApi

internal class PlayersRemoteResource(
    private val api: BasketballApi,
) {

    suspend fun fetchPlayers(pageNumber: Int): PageResult<List<Player>> {
        return PageResult.of {
            api
                .getPlayers(pageNumber = pageNumber, perPage = 35)
                .players.map(PlayerConverter::toDomain)
        }
    }

    suspend fun fetchPlayer(id: Int): PageResult<Player> = PageResult.of {
        api
            .getPlayer(id = id)
            .let(PlayerConverter::toDomain)
    }
}