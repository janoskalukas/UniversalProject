package universal.library.localisation.di

import org.koin.core.module.Module
import org.koin.core.module.dsl.new
import org.koin.dsl.module
import universal.library.localisation.device.LocalisationServiceImpl
import universal.library.localisation.domain.LocalisationService

/**
 * Localisation library module DI graph.
 */
public object LocalisationGraph {

    public val module: Module = module {
        factory<LocalisationService> { new(::LocalisationServiceImpl) }
    }
}