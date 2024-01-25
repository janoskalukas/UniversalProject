package universal.feature.basketball.routing

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import universal.feature.basketball.scene.PlayerDetailScreen
import universal.feature.basketball.scene.PlayersListScreen

@Composable
public fun BasketballNavGraph(navController: NavHostController, paddingValues: PaddingValues) {

    NavHost(navController = navController, startDestination = "players_list") {
        composable("players_list") { PlayersListScreen() }
        composable("player_detail") { PlayerDetailScreen() }
    }
}