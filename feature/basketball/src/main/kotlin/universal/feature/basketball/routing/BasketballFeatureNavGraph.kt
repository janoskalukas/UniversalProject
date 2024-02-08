package universal.feature.basketball.routing

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import universal.feature.basketball.scene.PlayerDetailScreen
import universal.feature.basketball.scene.PlayersListScreen

public fun NavGraphBuilder.basketballFeatureNavGraph() {
    composable(route = "players_list") {
        PlayersListScreen()
    }
    composable(route = "player_detail") {
        PlayerDetailScreen()
    }
}