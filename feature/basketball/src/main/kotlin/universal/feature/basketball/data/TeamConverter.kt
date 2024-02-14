package universal.feature.basketball.data

import universal.feature.basketball.model.Team
import universal.networking.nba.dto.ApiTeam

internal object TeamConverter {

    internal fun toDomain(external: ApiTeam): Team = with(external) {
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