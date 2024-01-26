package universal.feature.basketball.presentation

internal data class PlayerState(
    val id: Int,
    val fullName: String,
    val team: String,
    val position: String?,
    val imageUrl: String,
)