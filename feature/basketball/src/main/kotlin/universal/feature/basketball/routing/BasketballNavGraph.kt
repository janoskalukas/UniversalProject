package universal.feature.basketball.routing

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import universal.feature.basketball.scene.PlayerDetailScreen
import universal.feature.basketball.scene.PlayersListScreen

@Composable
public fun BasketballNavGraph(navController: NavHostController, paddingValues: PaddingValues) {

    NavHost(
        navController = navController,
        startDestination = "players_list",
        enterTransition = { fadeIn(tween(1000)) },
        popExitTransition = { fadeOut(tween(1000)) },
    ) {
        composable(route = "players_list") { PlayersListScreen() }
        composable(route = "player_detail") { PlayerDetailScreen() }
    }
}