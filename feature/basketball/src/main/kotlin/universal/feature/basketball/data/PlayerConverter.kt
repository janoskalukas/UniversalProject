package universal.feature.basketball.data

import universal.feature.basketball.model.Player
import universal.networking.basketball.dto.ApiPlayer

internal object PlayerConverter {

    fun toDomain(external: ApiPlayer): Player = with(external) {
        Player(
            id = id,
            position = position.let(PositionConverter::toDomain),
            firstName = firstName,
            lastName = lastName,
            heightFeet = heightFeet,
            heightInches = heightInches,
            weightPounds = weightPounds,
            imageUrl = "https://picsum.photos/300?random=$id",
            team = team.let(TeamConverter::toDomain),
        )
    }
}