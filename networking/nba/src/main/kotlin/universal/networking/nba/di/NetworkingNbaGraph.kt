package universal.networking.nba.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import retrofit2.Retrofit
import universal.networking.nba.BasketballApi
import universal.networking.nba.data.BasketballServiceApi

/**
 * Networking NBA DI graph.
 */
public object NetworkingNbaGraph {

    public val module = module {

        single<BasketballApi> { get<Retrofit>().create(BasketballApi::class.java) }
        factoryOf(::BasketballServiceApi)
    }
}