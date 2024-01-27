package universal.feature.basketball.domain

import universal.feature.basketball.model.Player
import universal.library.architecture.domain.SuspendUseCase
import universal.library.result.model.PageResult

internal interface PlayersUseCase {

    class Fetch(
        private val playersRepository: PlayersRepository,
    ) : SuspendUseCase<Int, PageResult<List<Player>>> {

        override suspend fun invoke(input: Int): PageResult<List<Player>> = playersRepository.fetchPlayers(input)
    }
}