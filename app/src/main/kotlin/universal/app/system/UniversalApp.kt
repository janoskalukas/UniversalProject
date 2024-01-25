package universal.app.system

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import universal.feature.basketball.di.BasketballFeatureGraph
import universal.library.navigation.di.NavigationLibraryGraph
import universal.networking.basketball.di.NetworkingGraph

internal class UniversalApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@UniversalApp)
            modules(
                listOf(
                    BasketballFeatureGraph.module,
                    NavigationLibraryGraph.module,
                    NetworkingGraph.module,
                ),
            )
        }
    }
}