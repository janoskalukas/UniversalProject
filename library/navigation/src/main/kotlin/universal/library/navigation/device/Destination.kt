package universal.library.navigation.device

/**
 * Identifies single destination of navigation graph.
 */
public sealed interface Destination {

    /** No destination in the graph or graph not set. */
    public object None : Destination

    /** Destination defined by [id]. */
    public data class Id(val id: Int) : Destination
}