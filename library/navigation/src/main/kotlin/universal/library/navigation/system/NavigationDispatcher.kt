package universal.library.navigation.system

import android.content.Intent
import universal.library.navigation.device.Destination
import universal.library.navigation.system.NavigationDispatcher.Request.Call
import universal.library.navigation.system.NavigationDispatcher.Request.CloseApp
import universal.library.navigation.system.NavigationDispatcher.Request.GoBack
import universal.library.navigation.system.NavigationDispatcher.Request.GoBackTo
import universal.library.navigation.system.NavigationDispatcher.Request.GoTo
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

/**
 * Launches one-shot navigation actions without waiting for result.
 */
@Suppress("TooManyFunctions")
public class NavigationDispatcher internal constructor() {

    private val requests = Channel<Request>(Channel.BUFFERED)
    private val currentDestination: MutableStateFlow<Destination> =
        MutableStateFlow(Destination.None)

    public fun goBack() {
        requests.trySend(GoBack)
    }

    public fun goBackTo(destination: String, popDestination: Boolean = false) {
        requests.trySend(GoBackTo(destination = destination, popDestination = popDestination))
    }

    public fun goTo(destination: String) {
        requests.trySend(GoTo(destination = destination))
    }

    public fun call(intent: Intent) {
        requests.trySend(Call(intent))
    }

    public fun closeApp() {
        requests.trySend(CloseApp)
    }

    internal fun observeRequests(): Flow<Request> = requests.receiveAsFlow()

    internal sealed interface Request {
        object GoBack : Request
        data class GoBackTo(val destination: String, val popDestination: Boolean = false) : Request
        data class GoTo(val destination: String) : Request
        data class Call(val intent: Intent) : Request
        object CloseApp : Request
    }

    public fun getCurrentDestination(): Destination = currentDestination.value
    public fun observeCurrentDestination(): Flow<Destination> = currentDestination.asStateFlow()

    internal fun onNavigationExecuted(current: Destination) {
        currentDestination.value = current
    }
}