package universal.feature.basketball.model

internal data class Team(
    val id: Int,
    val name: String,
    val fullName: String,
    val abbreviation: String,
    val city: String,
    val conference: String,
    val division: String,
)