package universal.feature.basketball.presentation

import universal.feature.basketball.model.Player

internal class PlayerFormat {

    internal fun format(player: Player): PlayerState = with(player) {
        PlayerState(
            id = id,
            fullName = "$firstName $lastName",
            team = "${team.city} ${team.name}",
            position = position.description,
            imageUrl = imageUrl,
        )
    }
}