package universal.library.result.model

/**
 * Domain model representation of generic business operation error.
 */
public sealed class Error {

    /**
     * Scope of an error, typically determines input field under which error is displayed
     */
    public data class Scope(val value: String) {

        public companion object {
            public val Undefined: Scope = Scope("undefined")
        }
    }

    /**
     * External error code.
     */
    public data class Code(val value: String)

    /**
     * Error meaning.
     */
    public enum class Category { Success, Info, Warning, Error }

    /**
     * Error reason, defines the origin of an error and available actions.
     */
    public sealed interface Cause {

        /** Error is caused by external system and defined by error [Code]. */
        public data class External(val code: Code) : Cause

        /** Error is caused by external system and defined by individual attributes. */
        public data class Universal(
            val title: String?,
            val reason: String?,
            val outcome: String?,
            val solution: String?,
            val illustration: String?,
            val helpAction: Action?,
            val primaryAction: Action?,
            val secondaryAction: Action?,
        ) : Cause {

            /** Possible action of universal error. */
            public sealed interface Action {

                public val label: String

                /** Application should navigate back. */
                public data class Back(override val label: String) : Action

                /** Application should call help line. */
                public data class Call(override val label: String) : Action

                /** Application should retry action. */
                public data class Retry(override val label: String) : Action
            }
        }

        /** Error is caused by external system denial (HTTP 403). */
        public data class AccessDenied(val variant: Variant) : Cause {

            /** Possible variant of access denied error containing a specific error [Code]. */
            public enum class Variant(public val code: Code) {
                /** Security level of requester is caller.jwt.auth_level. It is too low for requested access to protected resource. */
                AuthLevelLow(Code("ERR_PM_LOW_AUTH_LEVEL")),

                /** Client is locked by migration process to NDB. */
                LockedDueMigration(Code("ERR_PM_LOCK_MIG2NDB")),

                /** Client is locked by onboarding process to NDB. */
                LockedDueOnboarding(Code("ERR_PM_LOCK_ONB2NDB")),

                /** Customer does not have sufficient disposal rights to perform this action. */
                InsufficientDisposalRights(Code("ERR_PM_TRUSTEE_ACCESS_DENY")),

                /** Client has restricted rights to perform this action. */
                RightsRestriction(Code("ERR_PM_RIGHTS_RESTRICTION")),

                /** Active frame contract (RAS) is required. */
                FrameContractRequired(Code("ERR_PM_ACTIVE_RAS_REQUIRED")),

                /** Generic access denied error. */
                Generic(Code("ERR_403_FORBIDDEN")),
            }
        }

        /** Error is caused by external system denial with specific [caseId]. */
        public data class ConnectionTerminated(val code: String, val caseId: String) : Cause

        /** Error is caused by external system not available or operable (HTTP 500). */
        public data object Unavailability : Cause

        /** Error is caused by external system not reachable. */
        public data object Connectivity : Cause

        /** Error has unknown cause, i.e. when unexpected error happened or HTTP 400. */
        public data object Undefined : Cause
    }
}

/**
 * Single form field error, example: after input validation.
 */
public data class FieldError(val cause: Cause, val category: Category, val scope: Scope) : Error()

/**
 * Screen section error, example: after submit action.
 */
public data class SectionError(val cause: Cause, val category: Category) : Error()

/**
 * Screen dialog error, example: after non-screen primary action.
 */
public data class DialogError(val cause: Cause) : Error()

/**
 * Full screen error, example: after initial data fetch.
 */
public data class PageError(val cause: Cause) : Error()