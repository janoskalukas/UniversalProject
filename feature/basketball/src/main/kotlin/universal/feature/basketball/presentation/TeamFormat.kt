package universal.feature.basketball.presentation

import universal.feature.basketball.model.Team
import universal.library.localisation.domain.LocalisationService
import universal.library.localisation.infrastructure.string

internal class TeamFormat(
    private val localisationService: LocalisationService,
) {

    internal fun format(team: Team): TeamState = with(team) {
        TeamState(
            fullName = fullName,
            conference = localisationService.localise(string.conference, conference),
            division = localisationService.localise(string.division, division),
            city = localisationService.localise(string.city, city),
            abbreviation = localisationService.localise(string.abbreviation, abbreviation),
        )
    }
}