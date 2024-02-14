package universal.networking.nba.dto

import com.squareup.moshi.Json

/**
 * Contains player info for basketball api.
 */
public data class ApiPlayer(

    val id: Int,

    @field:Json(name = "position")
    val position: String,

    @field:Json(name = "first_name")
    val firstName: String,

    @field:Json(name = "last_name")
    val lastName: String,

    @field:Json(name = "height_feet")
    val heightFeet: Int?,

    @field:Json(name = "height_inches")
    val heightInches: Int?,

    @field:Json(name = "weight_pounds")
    val weightPounds: Int?,

    val team: ApiTeam,
)