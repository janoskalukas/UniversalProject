package universal.feature.basketball.data

import universal.feature.basketball.model.CurrentPage
import universal.feature.basketball.model.Players
import universal.networking.basketball.dto.ApiPlayers

internal object PlayersConverter {

    fun toDomain(external: ApiPlayers): Players = with(external) {
        Players(
            currentPage = CurrentPage(index = meta.currentPage, hasNext = meta.nextPage > meta.currentPage),
            players = players.map(PlayerConverter::toDomain)
        )
    }
}