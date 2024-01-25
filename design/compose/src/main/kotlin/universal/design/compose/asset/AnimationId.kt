package universal.design.compose.asset

import com.universal.design.compose.R

/**
 * Catalog of animations
 */
public enum class AnimationId(internal val code: String, public val resourceId: Int) {
    Spinner("Spinner", R.raw.lottie_spinner),
    ;

    public companion object {

        /**
         * Finds corresponding animation ID or returns null.
         */
        public fun fromCodeOrNull(code: String): AnimationId? = values().find { it.code == code }
    }
}