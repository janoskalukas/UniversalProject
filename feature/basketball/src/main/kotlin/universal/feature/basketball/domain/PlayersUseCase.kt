package universal.feature.basketball.domain

import androidx.paging.PagingData
import universal.library.architecture.domain.SuspendUnitUseCase
import universal.feature.basketball.model.Player
import kotlinx.coroutines.flow.Flow

internal interface PlayersUseCase {

    class Fetch(
        private val playersRepository: PlayersRepository,
    ) : SuspendUnitUseCase<Flow<PagingData<Player>>> {

        override suspend fun invoke(): Flow<PagingData<Player>> = playersRepository.getMovies()
    }
}