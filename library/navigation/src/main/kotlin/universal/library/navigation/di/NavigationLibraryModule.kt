package universal.library.navigation.di

import universal.library.navigation.system.NavigationActivityDelegate
import universal.library.navigation.system.NavigationDispatcher
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

public object NavigationLibraryModule {

    public val module = module {
        factoryOf(::NavigationActivityDelegate)
        singleOf(::NavigationDispatcher)
    }
}