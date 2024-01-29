package universal.feature.basketball.presentation

import io.kotest.matchers.shouldBe
import org.junit.Test
import universal.feature.basketball.localisationService
import universal.feature.basketball.model.Team
import universal.feature.basketball.teamFormat
import universal.library.localisation.fixtures.domain.localise
import universal.library.localisation.infrastructure.string

internal class TeamFormatTest {

    @Test
    fun `should format team to team state`() {
        val team = Team(
            id = 0,
            name = "",
            fullName = "Los Angeles Lakers",
            abbreviation = "abbreviation",
            city = "city",
            conference = "Western",
            division = "division",
        )

        teamFormat(
            localisationService = localisationService {
                localise(string.conference) { (conference) -> "Conference: $conference" }
                localise(string.division) { (division) -> "Division: $division" }
                localise(string.city) { (city) -> "City: $city" }
                localise(string.abbreviation) { (abbreviation) -> "Abbreviation: $abbreviation" }
            },
        ).format(team = team) shouldBe TeamState(
            fullName = "Los Angeles Lakers",
            conference = "Conference: Western",
            division = "Division: division",
            city = "City: city",
            abbreviation = "Abbreviation: abbreviation",
        )
    }
}