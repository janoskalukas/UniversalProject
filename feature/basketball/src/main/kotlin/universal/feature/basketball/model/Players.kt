package universal.feature.basketball.model

internal data class Players(
    val players: List<Player>,
    val meta: Meta,
) {
    data class Meta(
        val currentPage: Int,
        val nextPage: Int,
        val perPage: Int,
    )
}