package universal.feature.basketball.scene

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import org.koin.androidx.compose.koinViewModel
import universal.design.compose.component.FullScreenError
import universal.design.compose.component.FullScreenSpinner
import universal.design.compose.theme.PreviewTheme
import universal.feature.basketball.presentation.PlayerState
import universal.feature.basketball.scene.PlayerDetailViewModel.Content
import universal.library.mvvm.presentation.Lce

@Composable
internal fun PlayerDetailScreen(viewModel: PlayerDetailViewModel = koinViewModel()) {
    Screen(
        state = viewModel.states.collectAsState().value,
        onBack = viewModel::onBack,
        onRetry = viewModel::onRetry,
    )
}

@Composable
private fun Screen(
    state: Lce<Content>,
    onBack: () -> Unit,
    onRetry: () -> Unit,
) {
    BackHandler(onBack = onBack)
    when (state) {
        is Lce.Loading -> FullScreenSpinner()
        is Lce.Error -> FullScreenError(refresh = onRetry)
        is Lce.Content -> Content(content = state.content)
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun Content(content: Content) {
    Column {
        GlideImage(
            model = content.player.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
        )
        Column {
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = content.player.fullName,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            )

            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = content.player.team,
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )

            Spacer(modifier = Modifier.size(4.dp))
            content.player.position?.let {
                Text(
                    text = it,
                    modifier = Modifier
                        .padding(horizontal = 16.dp),
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}

@Preview(locale = "en", name = "Night Mode", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun ScreenPreviewDark() = PreviewTheme {
    Screen(
        state = Lce.Content(
            Content(
                PlayerState(
                    id = 0,
                    fullName = "Howard Wright",
                    team = "Atlanta Hawks",
                    position = "Center",
                    imageUrl = "",
                ),
            ),
        ),
        onBack = { },
        onRetry = {},
    )
}

@Preview(locale = "en", name = "Day Mode")
@Composable
private fun ScreenPreviewLight() = PreviewTheme {
    Screen(
        state = Lce.Content(
            Content(
                PlayerState(
                    id = 0,
                    fullName = "Howard Wright",
                    team = "Atlanta Hawks",
                    position = "Center",
                    imageUrl = "",
                ),
            ),
        ),
        onBack = { },
        onRetry = {},
    )
}