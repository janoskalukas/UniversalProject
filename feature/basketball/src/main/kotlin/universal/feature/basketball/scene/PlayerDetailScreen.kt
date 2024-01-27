package universal.feature.basketball.scene

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import universal.design.compose.component.ButtonOutlined
import universal.design.compose.component.FullScreenError
import universal.design.compose.component.FullScreenSpinner
import universal.design.compose.component.TextBodyMedium
import universal.design.compose.component.TextTitleLarge
import universal.design.compose.theme.PreviewTheme
import universal.feature.basketball.presentation.PlayerState
import universal.feature.basketball.presentation.TeamState
import universal.feature.basketball.scene.PlayerDetailViewModel.Content
import universal.library.mvvm.presentation.Lce

@Composable
internal fun PlayerDetailScreen(viewModel: PlayerDetailViewModel = koinViewModel()) {
    Screen(
        state = viewModel.states.collectAsState().value,
        onRetry = viewModel::onRetry,
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun Screen(
    state: Lce<Content>,
    onRetry: () -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmValueChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = false,
    )

    BackHandler(sheetState.isVisible) { coroutineScope.launch { sheetState.hide() } }
    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetShape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
        sheetContent = { BottomSheet(state = state) },
    ) {
        when (state) {
            is Lce.Loading -> FullScreenSpinner()
            is Lce.Error -> FullScreenError(refresh = onRetry)
            is Lce.Content -> Content(content = state.content, onTeam = { coroutineScope.launch { sheetState.show() } })
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun Content(
    content: Content,
    onTeam: (Int) -> Unit,
) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        content.player.apply {
            Box {
                GlideImage(
                    model = imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.height(200.dp),
                )
                ButtonOutlined(
                    text = team,
                    modifier = Modifier
                        .padding(end = 8.dp, bottom = 4.dp)
                        .align(Alignment.BottomEnd),
                    onClick = { onTeam(id) },
                )
            }
            Column {
                Spacer(modifier = Modifier.size(24.dp))
                TextTitleLarge(text = fullName)
                position?.let {
                    Spacer(modifier = Modifier.size(8.dp))
                    TextBodyMedium(text = it)
                }
                height?.let {
                    Spacer(modifier = Modifier.size(8.dp))
                    TextBodyMedium(text = it)
                }
                weight?.let {
                    Spacer(modifier = Modifier.size(8.dp))
                    TextBodyMedium(text = it)
                }
                Spacer(modifier = Modifier.size(16.dp))
            }
        }
    }
}

@Preview(locale = "en", name = "Night Mode", uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun ScreenPreviewDark() = PreviewTheme {
    Screen(
        state = Lce.Content(
            Content(
                player = PlayerState(
                    id = 0,
                    fullName = "Howard Wright",
                    team = "Atlanta Hawks",
                    teamId = 10,
                    position = "Center",
                    imageUrl = "",
                    height = "10′ 6″",
                    weight = "100 pounds",
                ),
                team = TeamState(fullName = "LA Clippers", conference = "", division = "", city = "", abbreviation = ""),
            ),
        ),
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
                    teamId = 10,
                    position = "Center",
                    imageUrl = "",
                    height = "10′ 6″",
                    weight = "100 pounds",
                ),
                team = TeamState(fullName = "LA Clippers", conference = "", division = "", city = "", abbreviation = ""),
            ),
        ),
        onRetry = {},
    )
}