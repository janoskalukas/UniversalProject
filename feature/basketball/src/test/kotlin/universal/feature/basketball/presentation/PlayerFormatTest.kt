package universal.feature.basketball.presentation

import io.kotest.matchers.shouldBe
import org.junit.Test
import universal.feature.basketball.localisationService
import universal.feature.basketball.model.Player
import universal.feature.basketball.model.Position
import universal.feature.basketball.model.Team
import universal.feature.basketball.playerFormat
import universal.library.localisation.fixtures.domain.localise
import universal.library.localisation.infrastructure.string

internal class PlayerFormatTest {

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

        playerFormat(
            localisationService = localisationService {
                localise(string.name) { (firstName, lastName) -> "$firstName $lastName" }
                localise(string.height) { (feet, inches) -> "Height: $feet′ $inches′′" }
                localise(string.weight) { (weight) -> "Weight: $weight lbs" }
            },
        ).format(player) shouldBe PlayerState(
            id = 88,
            fullName = "Kobe Bryant",
            team = "Los Angeles Lakers",
            teamId = 1,
            position = "Position: Center",
            imageUrl = "url",
            height = "Height: 7′ 6′′",
            weight = "Weight: 105 lbs",
        )
    }
}