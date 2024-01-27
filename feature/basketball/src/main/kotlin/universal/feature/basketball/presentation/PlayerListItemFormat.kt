package universal.feature.basketball.presentation

import universal.feature.basketball.model.Player
import universal.library.localisation.domain.LocalisationService
import universal.library.localisation.infrastructure.string

internal class PlayerListItemFormat(
    private val localisationService: LocalisationService,
    private val positionFormat: PositionFormat,
) {

    internal fun format(player: Player): PlayerListItemState = with(player) {
        PlayerListItemState(
            id = id,
            fullName = localisationService.localise(string.name, firstName, lastName),
            team = localisationService.localise(string.team, team.fullName),
            position = position.let(positionFormat::format),
            imageUrl = imageUrl,
        )
    }
}