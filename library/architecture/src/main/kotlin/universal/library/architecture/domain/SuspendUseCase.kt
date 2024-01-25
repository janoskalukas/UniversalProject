package universal.library.architecture.domain

/**
 * Standalone domain action that orchestrates the actual work.
 * Has no internal state and suspends on execution.
 * Takes given [Input] and returns [Output].
 */
public interface SuspendUseCase<in Input : Any, out Output : Any> {
    public suspend operator fun invoke(input: Input): Output
}

/**
 * Standalone domain action that orchestrates the actual work.
 * Has no internal state and suspends on execution.
 * Takes no input and returns [Output].
 */
public interface SuspendUnitUseCase<out Output : Any> {
    public suspend operator fun invoke(): Output
}