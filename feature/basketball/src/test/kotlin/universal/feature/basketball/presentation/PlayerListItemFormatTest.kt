package universal.feature.basketball.presentation

import io.kotest.matchers.shouldBe
import org.junit.Test
import universal.feature.basketball.localisationService
import universal.feature.basketball.model.Player
import universal.feature.basketball.model.Position
import universal.feature.basketball.model.Team
import universal.feature.basketball.playerListItemFormat
import universal.library.localisation.fixtures.domain.localise
import universal.library.localisation.infrastructure.string

internal class PlayerListItemFormatTest {

    @Test
    fun `should format player to player state`() {
        val player = Player(
            id = 88,
            position = Position.Center,
            firstName = "Kobe",
            lastName = "Bryant",
            heightFeet = 7,
            heightInches = 6,
            weightPounds = 105,
            imageUrl = "url",
            team = Team(
                id = 1,
                name = "",
                fullName = "Los Angeles Lakers",
                abbreviation = "",
                city = "",
                conference = "",
                division = "",
            ),
        )

        playerListItemFormat(
            localisationService = localisationService {
                localise(string.name) { (firstName, lastName) -> "$firstName $lastName" }
                localise(string.team) { (teamName) -> "Team: $teamName" }
            },
        ).format(player) shouldBe PlayerListItemState(
            id = 88,
            fullName = "Kobe Bryant",
            team = "Team: Los Angeles Lakers",
            position = "Position: Center",
            imageUrl = "url",
        )
    }
}