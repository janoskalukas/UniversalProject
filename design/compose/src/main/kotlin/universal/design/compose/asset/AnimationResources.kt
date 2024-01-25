package universal.design.compose.asset

import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition

/**
 * Load an LottieCompositionSpec from given animation ID.
 */
@Composable
public fun AnimationId.spec(): LottieCompositionSpec {
    return LottieCompositionSpec.RawRes(resourceId)
}

/**
 * Renders [animation] as [LottieAnimation] with given [height] centered horizontally.
 */
@Composable
public fun Animation(animation: AnimationId, modifier: Modifier = Modifier, height: Dp = Dp.Unspecified) {
    val composition by rememberLottieComposition(animation.spec())
    LottieAnimation(composition = composition, modifier = Modifier.height(height).then(modifier))
}