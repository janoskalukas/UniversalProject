package universal.feature.basketball.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import universal.feature.basketball.model.Player
import universal.networking.basketball.BasketballApi

class MoviePagingSource(
    private val api: BasketballApi,
) : PagingSource<Int, Player>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Player> {
        return try {
            val currentPage = params.key ?: 1
            val result = api.getPlayers(
                pageNumber = currentPage,
                perPage = 25,
            )
            LoadResult.Page(
                data = result.players.map(PlayerConverter::toDomain),
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (result.players.isEmpty()) null else result.meta.currentPage + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Player>): Int? {
        return state.anchorPosition
    }

}