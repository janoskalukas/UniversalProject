package universal.design.compose.component

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import universal.design.compose.asset.AnimationId
import universal.design.compose.asset.spec

/**
 * Spinner
 */
@Composable
public fun Spinner(modifier: Modifier = Modifier, size: Dp = 100.dp) {
    val composition by rememberLottieComposition(AnimationId.Spinner.spec())
    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        modifier = modifier.size(size),
    )
}