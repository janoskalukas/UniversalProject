package universal.feature.basketball.domain

import universal.feature.basketball.model.Player
import universal.library.architecture.domain.SuspendUseCase
import universal.library.result.model.PageResult

internal interface PlayersUseCase {

    class Fetch(
        private val repository: PlayersRepository,
    ) : SuspendUseCase<Int, PageResult<List<Player>>> {

        override suspend fun invoke(input: Int): PageResult<List<Player>> = repository.fetchPlayers(input)
    }
}