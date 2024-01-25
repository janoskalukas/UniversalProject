package universal.library.navigation.system

import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import universal.library.navigation.device.Destination
import universal.library.navigation.system.NavigationDispatcher.Request

/**
 * Activity navigation logic of every main application Activity.
 */
public class NavigationActivityDelegate internal constructor(
    private val navigationDispatcher: NavigationDispatcher,
) {
    private var destinationChangedListener: NavController.OnDestinationChangedListener? = null
    private var navigationRequestsJob: Job? = null

    public fun onResume(activity: NavControllerActivity) {
        val navigationController = activity.navController

        destinationChangedListener().let {
            destinationChangedListener = it
            navigationController.addOnDestinationChangedListener(it)
        }

        navigationRequestsJob?.cancel()
        navigationRequestsJob = activity.lifecycleScope.launch {
            navigationDispatcher.observeRequests().collect { request ->
                when (request) {
                    is Request.GoBack -> if (!navigationController.popBackStack()) activity.finishAndRemoveTask()
                    is Request.GoBackTo -> navigationController.popBackStack(
                        request.destination,
                        request.popDestination,
                    )

                    is Request.GoTo -> navigationController.navigate(request.destination)
                    is Request.Call -> activity.startActivity(request.intent)
                    is Request.CloseApp -> activity.finishAndRemoveTask()
                }
            }
        }
    }

    public fun onPause(activity: NavControllerActivity) {
        destinationChangedListener?.let {
            activity.navController.removeOnDestinationChangedListener(it)
            destinationChangedListener = null
        }

        navigationRequestsJob?.cancel()
        navigationRequestsJob = null
    }

    private fun destinationChangedListener() = NavController.OnDestinationChangedListener { _, current, _ ->
        navigationDispatcher.onNavigationExecuted(
            current = current.id.let(Destination::Id),
        )
    }
}