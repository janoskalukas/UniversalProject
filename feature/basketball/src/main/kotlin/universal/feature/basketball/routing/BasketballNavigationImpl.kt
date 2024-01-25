package universal.feature.basketball.routing

import universal.library.navigation.system.NavigationDispatcher
import universal.feature.basketball.domain.BasketballNavigation

internal class BasketballNavigationImpl(
    private val dispatcher: NavigationDispatcher,
) : BasketballNavigation {

    override fun goBack() {
        dispatcher.goBack()
    }

    override fun goToPlayerDetail() {
        dispatcher.goTo("player_detail")
    }
}