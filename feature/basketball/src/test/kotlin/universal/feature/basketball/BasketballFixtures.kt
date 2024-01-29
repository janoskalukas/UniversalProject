package universal.feature.basketball

import io.mockk.every
import io.mockk.mockk
import universal.feature.basketball.domain.BasketballNavigation
import universal.feature.basketball.model.Player
import universal.feature.basketball.model.Position
import universal.feature.basketball.model.Team
import universal.feature.basketball.presentation.PlayerFormat
import universal.feature.basketball.presentation.PlayerListItemFormat
import universal.feature.basketball.presentation.PositionFormat
import universal.feature.basketball.presentation.TeamFormat
import universal.library.localisation.domain.LocalisationService
import universal.library.localisation.infrastructure.string

internal fun navigation(): BasketballNavigation = mockk(relaxUnitFun = true)

public fun localisationService(block: LocalisationService.() -> Unit = {}): LocalisationService {
    return mockk(relaxed = true, block = block)
}

internal fun player(
    id: Int = 0,
) = Player(
    id = id,
    position = Position.Center,
    firstName = "",
    lastName = "",
    heightFeet = null,
    heightInches = null,
    weightPounds = null,
    imageUrl = "",
    team = Team(
        id = 0,
        name = "",
        fullName = "",
        abbreviation = "",
        city = "",
        conference = "",
        division = "",
    ),
)

internal fun teamFormat(localisationService: LocalisationService): TeamFormat = TeamFormat(
    localisationService = localisationService,
)

internal fun positionFormat(localisationService: LocalisationService): PositionFormat = PositionFormat(
    localisationService = localisationService,
)

internal fun playerFormat(localisationService: LocalisationService): PlayerFormat = PlayerFormat(
    localisationService = localisationService,
    positionFormat = positionFormat(localisationService { every { localise(string.center) } returns "Position: Center" }),
)

internal fun playerListItemFormat(localisationService: LocalisationService): PlayerListItemFormat = PlayerListItemFormat(
    localisationService = localisationService,
    positionFormat = positionFormat(localisationService { every { localise(string.center) } returns "Position: Center" }),
)