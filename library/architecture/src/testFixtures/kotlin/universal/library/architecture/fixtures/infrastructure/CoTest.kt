package universal.library.architecture.fixtures.infrastructure

import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.test.TestResult
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest

/**
 * Executes [testBody] as a test in a new coroutine, returning [TestResult].
 *
 * Wraps coroutines test library native [runTest] with extended [timeout] to make tests works in slow environments as CI.
 */
public fun coTest(
    context: CoroutineContext = EmptyCoroutineContext,
    timeout: Duration = 60.seconds,
    testBody: suspend TestScope.() -> Unit,
): TestResult = runTest(
    context = context,
    timeout = timeout,
    testBody = testBody,
)

/**
 * Executes [testBody] as a test in a new coroutine, returning [TestResult].
 *
 * Wraps coroutines test library native [runTest] with extended [timeout] to make tests works in slow environments as CI.
 */
public fun TestScope.coTest(
    timeout: Duration = 60.seconds,
    testBody: suspend TestScope.() -> Unit,
): TestResult = runTest(
    timeout = timeout,
    testBody = testBody,
)