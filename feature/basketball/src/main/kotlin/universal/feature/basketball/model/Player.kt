package universal.feature.basketball.model

internal data class Player(
    val id: Int,
    val position: String,
    val firstName: String,
    val lastName: String,
    val heightFeet: Int?,
    val heightInches: Int?,
    val weightPounds: Int?,
    val imageUrl: String,
    val team: Team,
)