package universal.feature.basketball.scene

import androidx.activity.compose.BackHandler
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.koinViewModel
import universal.design.compose.component.FullScreenError
import universal.design.compose.component.FullScreenSpinner
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

@Composable
private fun Content(content: Content) {
    Text(
        text = content.name,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        fontSize = 20.sp,
    )
}