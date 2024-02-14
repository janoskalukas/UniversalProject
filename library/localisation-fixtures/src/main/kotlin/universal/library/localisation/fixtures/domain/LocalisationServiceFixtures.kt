@file:Suppress("SpreadOperator", "TooManyFunctions")

package universal.library.localisation.fixtures.domain

import io.mockk.every
import io.mockk.mockk
import universal.library.localisation.domain.LocalisationService

public fun localisationService(block: LocalisationService.() -> Unit = {}): LocalisationService {
    return mockk(relaxed = true, block = block)
}

public infix fun Int.localiseTo(localised: String): LocalisationService = localisationService {
    every { localise(this@localiseTo) } returns localised
}

public infix fun String.localiseTo(localised: String?): LocalisationService = localisationService {
    every { localise(this@localiseTo) } returns localised
}

public infix fun String.localiseTo(localised: (Array<Any>) -> String?): LocalisationService = localisationService {
    localise(resource = this@localiseTo, localised = localised)
}

public infix fun Int.localiseTo(localised: (Array<Any>) -> String): LocalisationService = localisationService {
    localise(resource = this@localiseTo, localised = localised)
}

public fun LocalisationService.localise(resource: Int, localised: (Array<Any>) -> String) {
    val captured = mutableListOf<Any>()
    every { localise(resource, *varargAll { captured.add(it) }) } answers {
        val result = localised(captured.toTypedArray())
        captured.clear()
        result
    }
}

public fun LocalisationService.localise(resource: String, localised: (Array<Any>) -> String?) {
    val captured = mutableListOf<Any>()
    every { localise(resource, *varargAll { captured.add(it) }) } answers {
        val result = localised(captured.toTypedArray())
        captured.clear()
        result
    }
}