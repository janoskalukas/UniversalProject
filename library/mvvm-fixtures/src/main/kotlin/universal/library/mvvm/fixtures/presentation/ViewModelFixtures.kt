package universal.library.mvvm.fixtures.presentation

import io.kotest.matchers.types.shouldBeInstanceOf
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import universal.library.architecture.fixtures.infrastructure.collectValues
import universal.library.architecture.fixtures.infrastructure.lastValue
import universal.library.mvvm.presentation.Lce
import universal.library.mvvm.presentation.StatefulLceViewModel
import universal.library.mvvm.presentation.StatefulViewModel
import universal.library.mvvm.presentation.ViewModelContent
import universal.library.mvvm.presentation.ViewModelState

public fun <C : ViewModelContent> Lce<C>.asContent(): C = shouldBeInstanceOf<Lce.Content<C>>().content

public fun <C : ViewModelContent> List<Lce<C>>.asContents(): List<C> = map { it.asContent() }

@ExperimentalCoroutinesApi
public fun <S : ViewModelState> StatefulViewModel<S>.lastState(
    testScope: TestScope = TestScope(UnconfinedTestDispatcher()),
    block: suspend TestScope.() -> Unit = {},
): S = states.lastValue(testScope, block)

@ExperimentalCoroutinesApi
public fun <S : ViewModelState> StatefulViewModel<S>.collectStates(
    testScope: TestScope = TestScope(UnconfinedTestDispatcher()),
    block: suspend TestScope.() -> Unit = {},
): List<S> = states.collectValues(testScope, block)

@ExperimentalCoroutinesApi
public fun <C : ViewModelContent> StatefulLceViewModel<C>.lastContent(
    testScope: TestScope = TestScope(UnconfinedTestDispatcher()),
    block: suspend TestScope.() -> Unit = {},
): C = states.lastValue(testScope, block).asContent()

@ExperimentalCoroutinesApi
public fun <C : ViewModelContent> StatefulLceViewModel<C>.collectContents(
    testScope: TestScope = TestScope(UnconfinedTestDispatcher()),
    block: suspend TestScope.() -> Unit = {},
): List<C> = states.collectValues(testScope, block).asContents()