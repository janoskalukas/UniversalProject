package universal.library.architecture.domain


/**
 * Standalone domain action that orchestrates the actual work.
 * Has no internal state and blocks on execution.
 * Takes given [Input] and returns [Output].
 */
public interface UseCase<in Input : Any, out Output : Any> {
    public operator fun invoke(input: Input): Output
}

/**
 * Standalone domain action that orchestrates the actual work.
 * Has no internal state and blocks on execution.
 * Takes no input and returns [Output].
 */
public interface UnitUseCase<out Output : Any> {
    public operator fun invoke(): Output
}