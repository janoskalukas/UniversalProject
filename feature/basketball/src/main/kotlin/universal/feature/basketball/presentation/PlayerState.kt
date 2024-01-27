package universal.feature.basketball.presentation

internal data class PlayerState(
    val id: Int,
    val fullName: String,
    val team: String,
    val teamId: Int,
    val position: String?,
    val imageUrl: String,
    val height: String?,
    val weight: String?,
)