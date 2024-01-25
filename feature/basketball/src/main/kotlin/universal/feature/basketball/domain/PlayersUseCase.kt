package universal.feature.basketball.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import universal.feature.basketball.model.Player
import universal.library.architecture.domain.SuspendUnitUseCase

internal interface PlayersUseCase {

    class Fetch(
        private val playersRepository: PlayersRepository,
    ) : SuspendUnitUseCase<Flow<PagingData<Player>>> {

        override suspend fun invoke(): Flow<PagingData<Player>> = playersRepository.fetchPlayers()
    }
}