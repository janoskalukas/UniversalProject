package universal.app.scene

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import org.koin.android.ext.android.inject
import universal.design.compose.theme.UniversalTheme
import universal.feature.basketball.routing.BasketballNavGraph
import universal.library.navigation.system.NavControllerActivity
import universal.library.navigation.system.NavigationActivityDelegate

internal class MainActivity : NavControllerActivity() {

    private val navigationDelegate: NavigationActivityDelegate by inject()

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            UniversalTheme {
                Scaffold(
                    backgroundColor = MaterialTheme.colorScheme.background,
                ) {
                    BasketballNavGraph(navController = navController)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        navigationDelegate.onResume(this)
    }

    override fun onPause() {
        navigationDelegate.onPause(this)
        super.onPause()
    }
}