package universal.library.result.data

import universal.library.result.model.PageResult
import kotlin.coroutines.cancellation.CancellationException

/**
 * Wraps given suspending operation output as [PageResult].
 */
public suspend fun <T> PageResult.Companion.of(operation: suspend () -> T): PageResult<T> {
    return suspendRunCatching {
        PageResult.Success(operation())
    }.getOrElse { exception ->
        PageResult.Failure
    }
}

suspend fun <T, R> T.suspendRunCatching(block: suspend T.() -> R): Result<R> {
    return runCatching { block() }.onFailure { exception ->
        if (exception is CancellationException) throw exception
    }
}