package universal.library.paging.model

/**
 * Result object for paging operations.
 */
public sealed interface PagingResult<out T> {

    /**
     * Success with list of items.
     */
    public data class Success<out T>(val items: List<T>) : PagingResult<T>

    /**
     * [Failure] object.
     */
    public data object Failure : PagingResult<Nothing>
}