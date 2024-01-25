package universal.feature.basketball.routing

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import universal.feature.basketball.scene.HomeScreen
import universal.feature.basketball.scene.PlayerDetailScreen

@Composable
public fun BasketballNavGraph(navController: NavHostController, paddingValues: PaddingValues) {

    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomeScreen() }
        composable("player_detail") { PlayerDetailScreen() }
    }
}