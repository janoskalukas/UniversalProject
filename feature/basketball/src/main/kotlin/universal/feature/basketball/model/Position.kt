package universal.feature.basketball.model

internal enum class Position(val description: String?) {
    PointGuard("Point Guard"),
    ShootingGuard("Shooting Guard"),
    SmallForward("Small Forward"),
    PowerForward("Power Forward"),
    Center("Center"),
    ComboGuard("Combo Guard"),
    ForwardCenter("Forward Center"),
    GuardingForward("Guarding forward"),
    Forward("Forward"),
    Guard("Guard"),
    Unknown(null),
}