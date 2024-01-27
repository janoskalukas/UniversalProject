package universal.library.mvvm.presentation

/**
 * ViewModel that contains an instance of [ViewModelState] wrapped with [Lce].
 * Uses for simple screens where a handling of the loading status can be easily split to three phases.
 */
public abstract class StatefulLceViewModel<Content : ViewModelContent>(
    initialState: Lce<Content> = Lce.Loading(),
) : StatefulViewModel<Lce<Content>>(initialState) {

    protected var content: Content
        get() = (state as? Lce.Content)?.content ?: (state as? Lce.Loading)?.content ?: error("State is not Lce.Content but $state")
        set(value) {
            state = Lce.Content(value)
        }
}

/**
 * Common protocol for all LCE Content types.
 */
public interface ViewModelContent

/**
 * Loading/Content/Error (or LCE) is a pattern that may be used for achieving a better communication between Presentation and UI layers.
 * Ideally, for each simple screen, ViewModel should provide a View State wrapped with this class.
 */
public sealed class Lce<out Content : ViewModelContent> : ViewModelState {

    /**
     * Usually, the initial state, can have an empty (with default values) content.
     */
    public data class Loading<Content : ViewModelContent>(val content: Content? = null) : Lce<Content>()

    /**
     * Content is ready to be shown.
     */
    public data class Content<out Content : ViewModelContent>(val content: Content) : Lce<Content>()

    /**
     * Error is ready to be shown. It should contains a reason of failure but we are not going to do it now, for simplification.
     */
    public data object Error : Lce<Nothing>()
}