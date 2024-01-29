package universal.library.architecture.fixtures.domain

import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.yield
import universal.library.architecture.domain.SuspendUnitUseCase
import universal.library.architecture.domain.SuspendUseCase
import universal.library.architecture.domain.UnitUseCase
import universal.library.architecture.domain.UseCase

public inline fun <reified I : Any, reified U : UseCase<I, Unit>> mock(): U = mock(Unit)
public inline fun <reified I : Any, O, reified U : UseCase<I, O>> mock(value: O): U {
    return mockk { every { this@mockk.invoke(any()) } returns value }
}

public inline fun <reified U : UnitUseCase<Unit>> unitMock(): U = unitMock(Unit)
public inline fun <O, reified U : UnitUseCase<O>> unitMock(value: O): U {
    return mockk { every { this@mockk.invoke() } returns value }
}

public inline fun <reified I : Any, reified U : SuspendUseCase<I, Unit>> coMock(): U = coMock(Unit)
public inline fun <reified I : Any, O, reified U : SuspendUseCase<I, O>> coMock(value: O): U {
    return mockk { coEvery { this@mockk.invoke(any()) } coAnswers { yield(); value } }
}

public inline fun <reified U : SuspendUnitUseCase<Unit>> coUnitMock(): U = coUnitMock(Unit)
public inline fun <O, reified U : SuspendUnitUseCase<O>> coUnitMock(value: O): U {
    return mockk { coEvery { this@mockk.invoke() } coAnswers { yield(); value } }
}