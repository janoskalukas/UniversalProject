package universal.library.architecture.fixtures.infrastructure

import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher

@ExperimentalCoroutinesApi
public fun <T> Flow<T>.lastValue(
    testScope: TestScope = TestScope(UnconfinedTestDispatcher()),
    block: suspend TestScope.() -> Unit = {},
): T = collectValues(testScope, block).last()

@ExperimentalCoroutinesApi
public fun <T> Flow<T>.collectValues(
    testScope: TestScope = TestScope(UnconfinedTestDispatcher()),
    block: suspend TestScope.() -> Unit = {},
): List<T> {
    val values = mutableListOf<T>()
    testScope.coTest {
        val job = launch {
            collect { value -> values.add(value) }
        }
        block()
        job.cancel()
    }
    return values.toList()
}

/**
 * Executes the given function [block] and returns the duration of elapsed test virtual time interval.
 */
@ExperimentalCoroutinesApi
public inline fun TestScope.measureVirtualTime(block: () -> Unit): Duration {
    return testScheduler.measureVirtualTime(block)
}

/**
 * Executes the given function [block] and returns the duration of elapsed test virtual time interval.
 */
@ExperimentalCoroutinesApi
public inline fun TestCoroutineScheduler.measureVirtualTime(block: () -> Unit): Duration {
    val start = currentTime
    block()
    return (currentTime - start).milliseconds
}