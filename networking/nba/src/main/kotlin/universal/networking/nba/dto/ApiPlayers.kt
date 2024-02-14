package universal.networking.nba.dto

import com.squareup.moshi.Json

/**
 * Contains players info for basketball api.
 */
public data class ApiPlayers(

    @field:Json(name = "data")
    val players: List<ApiPlayer>,

    val meta: ApiMeta,
)