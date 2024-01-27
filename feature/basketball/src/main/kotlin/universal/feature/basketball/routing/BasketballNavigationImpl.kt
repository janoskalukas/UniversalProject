package universal.feature.basketball.routing

import universal.feature.basketball.domain.BasketballNavigation
import universal.library.navigation.system.NavigationDispatcher

internal class BasketballNavigationImpl(
    private val dispatcher: NavigationDispatcher,
) : BasketballNavigation {

    override fun goToPlayerDetail() {
        dispatcher.goTo("player_detail")
    }

    override fun goToTeamDetail() {
        dispatcher.goTo("team_detail")
    }
}