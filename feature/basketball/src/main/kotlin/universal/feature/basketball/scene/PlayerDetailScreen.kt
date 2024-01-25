package universal.feature.basketball.scene

import androidx.activity.compose.BackHandler
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun PlayerDetailScreen(viewModel: PlayerDetailViewModel = koinViewModel()) {
    val state = viewModel.states.collectAsState().value
    BackHandler(onBack = viewModel::onBack)
    Text(
        text = state.name,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        fontSize = 20.sp,
    )
}