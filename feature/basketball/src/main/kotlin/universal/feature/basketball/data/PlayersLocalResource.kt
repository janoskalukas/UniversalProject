package universal.feature.basketball.data

internal class PlayersLocalResource {

    private var currentPlayerId: Int? = null

    internal fun storePlayerId(id: Int) {
        currentPlayerId = id
    }
    internal fun loadPlayerId() = checkNotNull(currentPlayerId) { "Player id has not been set yet" }
}