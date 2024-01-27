package universal.feature.basketball.scene

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import universal.design.compose.component.ParagraphSmall
import universal.design.compose.component.TextTitleLarge
import universal.feature.basketball.scene.PlayerDetailViewModel.Content
import universal.library.mvvm.presentation.Lce

@Composable
internal fun BottomSheet(state: Lce<Content>) {
    (state as? Lce.Content)?.content?.let { content ->
        content.team.apply {
            Column(
                modifier = Modifier.background(MaterialTheme.colorScheme.background),
            ) {
                Spacer(modifier = Modifier.size(16.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    TextTitleLarge(text = fullName)
                    Spacer(modifier = Modifier.size(4.dp))
                    Divider(modifier = Modifier.padding(horizontal = 16.dp))
                }
                Spacer(modifier = Modifier.size(16.dp))
                ParagraphSmall(text = conference)
                Spacer(modifier = Modifier.size(8.dp))
                ParagraphSmall(text = division)
                Spacer(modifier = Modifier.size(8.dp))
                ParagraphSmall(text = city)
                Spacer(modifier = Modifier.size(8.dp))
                ParagraphSmall(text = abbreviation)
                Spacer(modifier = Modifier.size(16.dp))
            }
        }
    }
}