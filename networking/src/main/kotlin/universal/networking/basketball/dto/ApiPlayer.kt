package universal.networking.basketball.dto

import com.squareup.moshi.Json

data class ApiPlayer(

    val id: Int,
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