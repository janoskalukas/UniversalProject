package universal.library.result.model

/**
 * Result object for operations that load data for screen content where non-success state is presented as page error.
 */
public sealed class PageResult<out T> {

    /**
     * Success with given [value].
     */
    public data class Success<out T>(val value: T) : PageResult<T>()

    /**
     * Failure with given [PageError].
     */
//    public data class Failure(val error: PageError) : PageResult<Nothing>()

    public data object Failure : PageResult<Nothing>()

    public companion object
}

/**
 * Result object for operations that make actions on screen content where non-success state is presented as a separate dialog.
 */
public sealed class DialogResult<out T> {

    /**
     * Success with given [value].
     */
    public data class Success<out T>(val value: T) : DialogResult<T>()

    /**
     * Failure with given [DialogError].
     */
    public data object Failure : DialogResult<Nothing>()

    public companion object
}

/**
 * Result object for operations that make actions on screen content where non-success state is presented as section error.
 */
public sealed class SectionResult<out T> {

    /**
     * Success with given [value].
     */
    public data class Success<out T>(val value: T) : SectionResult<T>()

    /**
     * Failure with given [SectionError].
     */
    public data object Failure : SectionResult<Nothing>()

    public companion object
}

/**
 * Result object for operations that make actions on input fields where non-success state is presented as text below input field.
 */
public sealed class FieldResult<out T> {

    /**
     * Success with given [value].
     */
    public data class Success<out T>(val value: T) : FieldResult<T>()

    /**
     * Failure with given [FieldError].
     */
    public data object Failure : FieldResult<Nothing>()

    public companion object
}