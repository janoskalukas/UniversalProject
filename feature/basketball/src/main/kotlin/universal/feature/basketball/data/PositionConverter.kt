package universal.feature.basketball.data

import universal.feature.basketball.model.Position
import universal.feature.basketball.model.Position.Center
import universal.feature.basketball.model.Position.ComboGuard
import universal.feature.basketball.model.Position.Forward
import universal.feature.basketball.model.Position.ForwardCenter
import universal.feature.basketball.model.Position.Guard
import universal.feature.basketball.model.Position.GuardingForward
import universal.feature.basketball.model.Position.PointGuard
import universal.feature.basketball.model.Position.PowerForward
import universal.feature.basketball.model.Position.ShootingGuard
import universal.feature.basketball.model.Position.SmallForward
import universal.feature.basketball.model.Position.Unknown

internal object PositionConverter {

    internal fun toDomain(positionLetter: String): Position = when (positionLetter) {
        "P" -> PointGuard
        "SG" -> ShootingGuard
        "SF" -> SmallForward
        "PF" -> PowerForward
        "C" -> Center
        "CG" -> ComboGuard
        "F-C" -> ForwardCenter
        "G-F" -> GuardingForward
        "F" -> Forward
        "G" -> Guard
        else -> Unknown
    }
}