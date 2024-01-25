package universal.feature.basketball.data

import universal.feature.basketball.model.Player
import universal.feature.basketball.model.Team
import universal.networking.basketball.dto.ApiPlayer
import universal.networking.basketball.dto.ApiTeam

internal object PlayerConverter {

    fun toDomain(external: ApiPlayer): Player = with(external) {
        Player(
            id = id,
            position = position,
            firstName = firstName,
            lastName = lastName,
            heightFeet = heightFeet,
            heightInches = heightInches,
            weightPounds = weightPounds,
            team = team.let(PlayerConverter::toDomain)
        )
    }

    private fun toDomain(external: ApiTeam): Team = with(external) {
        Team(
            id = id,
            fullName = fullName,
            name = name,
            abbreviation = abbreviation,
            city = city,
            conference = conference,
            division = division,
        )
    }
}