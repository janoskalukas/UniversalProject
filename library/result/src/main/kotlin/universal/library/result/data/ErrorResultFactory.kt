package universal.library.result.data

import kotlin.coroutines.cancellation.CancellationException
import universal.library.result.model.PageResult

/**
 * Wraps given suspending operation output as [PageResult].
 */
public suspend fun <T> PageResult.Companion.of(operation: suspend () -> T): PageResult<T> {
    return suspendRunCatching {
        PageResult.Success(operation())
    }.getOrElse { _ ->
        PageResult.Failure
    }
}

suspend fun <T, R> T.suspendRunCatching(block: suspend T.() -> R): Result<R> {
    return runCatching { block() }.onFailure { exception ->
        if (exception is CancellationException) throw exception
    }
}