package universal.library.navigation.system

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.navigation.NavHostController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.DialogNavigator

/**
 * Activity with savable [NavHostController].
 */
public open class NavControllerActivity : ComponentActivity() {

    public val navController: NavHostController by lazy {
        createNavController(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState).apply {
            savedInstanceState?.getBundle(NavControllerState)?.let { navController.restoreState(it) }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBundle(NavControllerState, navController.saveState())
    }

    private fun createNavController(context: Context) =
        NavHostController(context).apply {
            navigatorProvider.addNavigator(ComposeNavigator())
            navigatorProvider.addNavigator(DialogNavigator())
        }

    private companion object {
        const val NavControllerState = "navControllerState"
    }
}