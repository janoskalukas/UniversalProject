package universal.app.system

import android.app.Application
import universal.library.navigation.di.NavigationLibraryModule
import universal.feature.basketball.di.BasketballFeatureModule
import universal.networking.basketball.di.NetworkingModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class UniversalApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@UniversalApp)
            modules(
                listOf(
                    BasketballFeatureModule.module,
                    NavigationLibraryModule.module,
                    NetworkingModule.module,
                )
            )
        }
    }
}