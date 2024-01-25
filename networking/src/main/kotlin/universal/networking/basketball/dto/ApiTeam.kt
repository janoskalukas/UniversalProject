package universal.networking.basketball.dto

import com.squareup.moshi.Json

data class ApiTeam(
    val id: Int,
    val name: String,
    @field:Json(name = "full_name")
    val fullName: String,
    val abbreviation: String,
    val city: String,
    val conference: String,
    val division: String,
)