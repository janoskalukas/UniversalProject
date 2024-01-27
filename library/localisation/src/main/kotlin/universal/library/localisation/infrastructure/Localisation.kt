@file:Suppress("Filename")

package universal.library.localisation.infrastructure

import com.universal.library.localisation.R

/**
 * Common accessor to localised string resources stored in :localisation module.
 * Enables to use `android.nonTransitiveRClass` Gradle flag that isolates resources usage across modules and improves build times.
 */
public typealias string = R.string