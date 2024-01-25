package universal.networking.basketball.dto

import com.squareup.moshi.Json

data class ApiPlayers(

    @field:Json(name = "data")
    val players: List<ApiPlayer>,

    val meta: ApiMeta
)