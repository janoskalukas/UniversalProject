package universal.library.navigation.di

import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import universal.library.navigation.system.NavigationActivityDelegate
import universal.library.navigation.system.NavigationDispatcher

/**
 * Navigation library DI graph.
 */
public object NavigationLibraryGraph {

    public val module = module {
        factoryOf(::NavigationActivityDelegate)
        singleOf(::NavigationDispatcher)
    }
}