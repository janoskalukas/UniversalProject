package universal.networking.nba.data

import universal.networking.nba.NbaApi
import universal.networking.nba.dto.ApiPlayer
import universal.networking.nba.dto.ApiPlayers

/**
 * Basketball service api.
 */
public class NbaServiceApi internal constructor(
    private val api: NbaApi,
) {

    suspend fun getPlayers(pageNumber: Int, perPage: Int): ApiPlayers {
        return api.getPlayers(pageNumber = pageNumber, perPage = perPage)
    }

    suspend fun getPlayer(id: Int): ApiPlayer {
        return api.getPlayer(id = id)
    }
}