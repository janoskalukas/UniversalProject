package universal.networking.nba.data

import universal.networking.nba.BasketballApi
import universal.networking.nba.dto.ApiPlayer
import universal.networking.nba.dto.ApiPlayers

/**
 * Basketball service api.
 */
public class BasketballServiceApi internal constructor(
    private val api: BasketballApi,
) {

    suspend fun getPlayers(pageNumber: Int, perPage: Int): ApiPlayers {
        return api.getPlayers(pageNumber = pageNumber, perPage = perPage)
    }

    suspend fun getPlayer(id: Int): ApiPlayer {
        return api.getPlayer(id = id)
    }
}