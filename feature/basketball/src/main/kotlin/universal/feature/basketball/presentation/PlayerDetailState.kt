package universal.feature.basketball.presentation

internal data class PlayerDetailState(
    val id: Int,
    val fullName: String,
    val team: String,
    val position: String?,
    val imageUrl: String,
    val height: String?,
)