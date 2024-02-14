package universal.networking.nba.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import retrofit2.Retrofit
import universal.networking.nba.NbaApi
import universal.networking.nba.data.NbaServiceApi

/**
 * Networking NBA DI graph.
 */
public object NetworkingNbaGraph {

    public val module = module {

        single<NbaApi> { get<Retrofit>().create(NbaApi::class.java) }
        factoryOf(::NbaServiceApi)
    }
}