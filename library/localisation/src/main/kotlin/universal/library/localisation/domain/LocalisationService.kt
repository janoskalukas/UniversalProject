package universal.library.localisation.domain

import androidx.annotation.ArrayRes
import androidx.annotation.StringRes

/**
 * Localizes string resources.
 */
public interface LocalisationService {

    /**
     * Translates Android string resource ID to its value using given formatting arguments.
     * @return Localized value of corresponding string resource.
     */
    public fun localise(@StringRes resourceId: Int, vararg formatArgs: Any): String

    /**
     * Translates Android string resource name to its value using given formatting arguments.
     * @return Localized value of corresponding string resource or null if not found.
     */
    public fun localise(resourceName: String, vararg formatArgs: Any): String?

    /**
     * Translates Android string array resource ID to its value.
     * @return Localized value of corresponding string array resource or null if not found.
     */
    public fun localiseArray(@ArrayRes resourceId: Int): Array<String>

    /**
     * Translates Android string array resource name to its value.
     * @return Localized value of corresponding string array resource or null if not found.
     */
    public fun localiseArray(resourceName: String): Array<String>?
}