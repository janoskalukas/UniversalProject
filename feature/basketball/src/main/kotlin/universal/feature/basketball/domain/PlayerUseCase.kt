package universal.feature.basketball.domain

import universal.library.architecture.domain.SuspendUnitUseCase
import universal.library.architecture.domain.UseCase
import universal.library.result.model.PageResult
import universal.feature.basketball.model.Player

internal interface PlayerUseCase {

    class Display(
        private val repository: PlayersRepository,
        private val navigation: BasketballNavigation,
    ) : UseCase<Int, Unit> {

        override fun invoke(input: Int) {
            repository.storePlayerId(input)
            navigation.goToPlayerDetail()
        }
    }

    class Fetch(
        private val repository: PlayersRepository,
    ) : SuspendUnitUseCase<PageResult<Player>> {

        override suspend fun invoke(): PageResult<Player> = repository.fetchPlayer()
    }
}