package universal.networking.nba

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import universal.networking.nba.dto.ApiPlayer
import universal.networking.nba.dto.ApiPlayers

internal interface NbaApi {

    @GET("players")
    suspend fun getPlayers(
        @Query("page") pageNumber: Int,
        @Query("per_page") perPage: Int,
    ): ApiPlayers

    @GET("players/{ID}")
    suspend fun getPlayer(@Path("ID") id: Int): ApiPlayer
}