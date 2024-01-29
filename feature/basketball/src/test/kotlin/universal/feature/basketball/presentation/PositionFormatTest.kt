package universal.feature.basketball.presentation

import io.kotest.matchers.shouldBe
import io.mockk.every
import junitparams.JUnitParamsRunner
import junitparams.Parameters
import org.junit.Test
import org.junit.runner.RunWith
import universal.feature.basketball.localisationService
import universal.feature.basketball.model.Position
import universal.feature.basketball.positionFormat
import universal.library.localisation.infrastructure.string

@RunWith(JUnitParamsRunner::class)
internal class PositionFormatTest {

    @Test
    @Parameters(method = "position to state")
    fun `should format position to string`(position: Position, text: String?) {
        positionFormat(
            localisationService {
                every { localise(string.point_guard) } returns "Position: Point guard"
                every { localise(string.shooting_guard) } returns "Position: Shooting guard"
                every { localise(string.small_forward) } returns "Position: Small forward"
                every { localise(string.power_forward) } returns "Position: Power forward"
                every { localise(string.center) } returns "Position: Center"
                every { localise(string.combo_guard) } returns "Position: Combo guard"
                every { localise(string.forward_center) } returns "Position: Forward center"
                every { localise(string.guarding_forward) } returns "Position: Guarding forward"
                every { localise(string.forward) } returns "Position: Forward"
                every { localise(string.guard) } returns "Position: Guard"
            },
        ).format(position) shouldBe text
    }

    @Suppress("unused")
    private fun `position to state`(): Any = setOf(
        arrayOf(Position.PointGuard, "Position: Point guard"),
        arrayOf(Position.ShootingGuard, "Position: Shooting guard"),
        arrayOf(Position.SmallForward, "Position: Small forward"),
        arrayOf(Position.PowerForward, "Position: Power forward"),
        arrayOf(Position.Center, "Position: Center"),
        arrayOf(Position.ComboGuard, "Position: Combo guard"),
        arrayOf(Position.ForwardCenter, "Position: Forward center"),
        arrayOf(Position.GuardingForward, "Position: Guarding forward"),
        arrayOf(Position.Forward, "Position: Forward"),
        arrayOf(Position.Guard, "Position: Guard"),
        arrayOf(Position.Unknown, null),
    )
}