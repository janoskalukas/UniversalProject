package universal.feature.basketball.presentation

import universal.feature.basketball.model.Position
import universal.library.localisation.domain.LocalisationService
import universal.library.localisation.infrastructure.string

internal class PositionFormat(
    private val localisationService: LocalisationService,
) {

    internal fun format(position: Position): String? = when (position) {
        Position.PointGuard -> localisationService.localise(string.point_guard)
        Position.ShootingGuard -> localisationService.localise(string.shooting_guard)
        Position.SmallForward -> localisationService.localise(string.small_forward)
        Position.PowerForward -> localisationService.localise(string.power_forward)
        Position.Center -> localisationService.localise(string.center)
        Position.ComboGuard -> localisationService.localise(string.combo_guard)
        Position.ForwardCenter -> localisationService.localise(string.forward_center)
        Position.GuardingForward -> localisationService.localise(string.guarding_forward)
        Position.Forward -> localisationService.localise(string.forward)
        Position.Guard -> localisationService.localise(string.guard)
        Position.Unknown -> null
    }
}