package universal.feature.basketball.model

data class Player(

    val id: Int,
    val position: String,
    val firstName: String,
    val lastName: String,
    val heightFeet: Int?,
    val heightInches: Int?,
    val weightPounds: Int?,
    val team: Team,
)