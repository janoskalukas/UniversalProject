package universal.feature.basketball.presentation

import universal.feature.basketball.model.Player

internal class PlayerDetailFormat {

    internal fun format(player: Player): PlayerDetailState = with(player) {
        PlayerDetailState(
            id = id,
            fullName = "$firstName $lastName",
            team = "${team.city} ${team.name}",
            position = position.description,
            imageUrl = imageUrl,
            height = toHeight(heightFeet, heightInches),
        )
    }

    private fun toHeight(feet: Int?, inches: Int?) = when {
        (feet == null || inches == null) -> null
        else -> "${feet}′ ${inches}″"
    }
}