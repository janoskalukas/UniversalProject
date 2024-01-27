package universal.feature.basketball.presentation

import universal.feature.basketball.model.Player
import universal.library.localisation.domain.LocalisationService
import universal.library.localisation.infrastructure.string

internal class PlayerFormat(
    private val localisationService: LocalisationService,
    private val positionFormat: PositionFormat,
) {

    internal fun format(player: Player): PlayerState = with(player) {
        PlayerState(
            id = id,
            fullName = localisationService.localise(string.name, firstName, lastName),
            team = team.fullName,
            teamId = player.team.id,
            position = position.let(positionFormat::format),
            imageUrl = imageUrl,
            height = formatHeight(heightFeet, heightInches),
            weight = weightPounds?.let { localisationService.localise(string.weight, it) },
        )
    }

    private fun formatHeight(feet: Int?, inches: Int?) = when {
        (feet == null || inches == null) -> null
        else -> localisationService.localise(string.height, feet, inches)
    }
}