package universal.feature.basketball.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.new
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import universal.feature.basketball.data.PlayersLocalResource
import universal.feature.basketball.data.PlayersRemoteResource
import universal.feature.basketball.data.PlayersRepositoryImpl
import universal.feature.basketball.domain.BasketballNavigation
import universal.feature.basketball.domain.PlayerUseCase
import universal.feature.basketball.domain.PlayersRepository
import universal.feature.basketball.domain.PlayersUseCase
import universal.feature.basketball.presentation.PlayerFormat
import universal.feature.basketball.presentation.PlayerListItemFormat
import universal.feature.basketball.presentation.PositionFormat
import universal.feature.basketball.presentation.TeamFormat
import universal.feature.basketball.routing.BasketballNavigationImpl
import universal.feature.basketball.scene.PlayerDetailViewModel
import universal.feature.basketball.scene.PlayersListViewModel

/**
 * Basketball feature DI graph.
 */
public object BasketballFeatureGraph {

    public val module = module {

        single<PlayersRepository> { new(::PlayersRepositoryImpl) }
        singleOf(::PlayersRemoteResource)
        singleOf(::PlayersLocalResource)

        factoryOf(PlayersUseCase::Fetch)
        factoryOf(PlayerUseCase::Display)
        factoryOf(PlayerUseCase::Fetch)

        factoryOf(::PlayerListItemFormat)
        factoryOf(::PlayerFormat)
        factoryOf(::PositionFormat)
        factoryOf(::TeamFormat)

        viewModelOf(::PlayersListViewModel)
        viewModelOf(::PlayerDetailViewModel)

        factory<BasketballNavigation> { new(::BasketballNavigationImpl) }
    }
}