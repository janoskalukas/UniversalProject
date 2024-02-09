package universal.networking.basketball.data

import universal.networking.basketball.BasketballApi
import universal.networking.basketball.dto.ApiPlayer
import universal.networking.basketball.dto.ApiPlayers

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