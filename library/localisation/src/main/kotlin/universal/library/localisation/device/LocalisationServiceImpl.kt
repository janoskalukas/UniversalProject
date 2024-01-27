package universal.library.localisation.device

import android.content.Context
import androidx.annotation.ArrayRes
import androidx.annotation.StringRes
import universal.library.localisation.domain.LocalisationService

internal class LocalisationServiceImpl(private val context: Context) : LocalisationService {

    override fun localise(@StringRes resourceId: Int, vararg formatArgs: Any): String {
        return if (formatArgs.isEmpty()) {
            context.getString(resourceId)
        } else {
            context.getString(resourceId, *formatArgs)
        }
    }

    override fun localise(resourceName: String, vararg formatArgs: Any): String? = runCatching {
        val resourceId = context.getStringResourceId(resourceName)
        resourceId?.let { localise(resourceId, *formatArgs) }
    }.getOrNull()

    override fun localiseArray(@ArrayRes resourceId: Int): Array<String> {
        return context.resources.getStringArray(resourceId)
    }

    override fun localiseArray(resourceName: String): Array<String>? = runCatching {
        val resourceId = context.getArrayResourceId(resourceName)
        resourceId?.let { localiseArray(resourceId) }
    }.getOrNull()

    private fun Context.getStringResourceId(resourceName: String) = resources
        .getIdentifier(resourceName, "string", context.packageName)
        .takeUnless { it == 0 }

    private fun Context.getArrayResourceId(resourceName: String) = resources
        .getIdentifier(resourceName, "array", context.packageName)
        .takeUnless { it == 0 }
}