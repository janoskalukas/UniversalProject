package universal.feature.basketball.di

import universal.feature.basketball.data.MoviePagingSource
import universal.feature.basketball.data.PlayersLocalResource
import universal.feature.basketball.data.PlayersRemoteResource
import universal.feature.basketball.data.PlayersRepositoryImpl
import universal.feature.basketball.domain.BasketballNavigation
import universal.feature.basketball.domain.PlayerUseCase
import universal.feature.basketball.domain.PlayersRepository
import universal.feature.basketball.domain.PlayersUseCase
import universal.feature.basketball.routing.BasketballNavigationImpl
import universal.feature.basketball.scene.HomeViewModel
import universal.feature.basketball.scene.PlayerDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.new
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

public object BasketballFeatureModule {

    public val module = module {

        single<PlayersRepository> { new(::PlayersRepositoryImpl) }
        singleOf(::PlayersRemoteResource)
        singleOf(::PlayersLocalResource)
        singleOf(::MoviePagingSource)

        viewModelOf(::HomeViewModel)
        viewModelOf(::PlayerDetailViewModel)

        factoryOf(PlayersUseCase::Fetch)
        factoryOf(PlayerUseCase::Display)
        factoryOf(PlayerUseCase::Fetch)

        factory<BasketballNavigation> { new(::BasketballNavigationImpl) }
    }
}