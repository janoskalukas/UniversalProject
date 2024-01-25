package universal.networking.basketball

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import universal.networking.basketball.dto.ApiPlayer
import universal.networking.basketball.dto.ApiPlayers

/**
 * Basketball api.
 */
interface BasketballApi {

    companion object {
        const val API_URL = "https://www.balldontlie.io/api/v1/"
    }

    @GET("players")
    suspend fun getPlayers(
        @Query("page") pageNumber: Int,
        @Query("per_page") perPage: Int,
    ): ApiPlayers

    @GET("players/{ID}")
    suspend fun getPlayer(@Path("ID") playerId: Int): ApiPlayer
}