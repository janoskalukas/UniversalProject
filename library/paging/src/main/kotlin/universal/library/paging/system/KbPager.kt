package universal.library.paging.system

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import universal.library.paging.model.PagingResult

public fun <V : Any> createPager(
    block: suspend (Int) -> PagingResult<V>,
): Pager<Int, V> = Pager(
    config = PagingConfig(pageSize = 1, prefetchDistance = 1),
    pagingSourceFactory = {
        object : PagingSource<Int, V>() {

            override fun getRefreshKey(state: PagingState<Int, V>) = state.anchorPosition

            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, V> {
                val currentPage = params.key ?: 1

                return when (val result = block(currentPage)) {
                    is PagingResult.Success -> LoadResult.Page(
                        data = result.items,
                        prevKey = if (currentPage == 1) null else currentPage - 1,
                        nextKey = if (result.items.isEmpty()) null else currentPage + 1,
                    )
                    is PagingResult.Failure -> LoadResult.Error(Throwable())
                }
            }
        }
    },
)