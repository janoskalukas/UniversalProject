package universal.design.compose.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun TextBodyMedium(text: String, modifier: Modifier = Modifier) {
    AuxText(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        modifier = modifier.padding(horizontal = 16.dp),
    )
}

@Composable
fun TextBodySmall(text: String, modifier: Modifier = Modifier) {
    AuxText(
        text = text,
        style = MaterialTheme.typography.bodySmall,
        modifier = modifier.padding(horizontal = 16.dp),
    )
}

@Composable
fun TextTitleLarge(text: String, modifier: Modifier = Modifier) {
    AuxText(
        text = text,
        style = MaterialTheme.typography.titleLarge,
        modifier = modifier.padding(horizontal = 16.dp),
    )
}

@Composable
fun TextTitleMedium(text: String, modifier: Modifier = Modifier) {
    AuxText(
        text = text,
        style = MaterialTheme.typography.titleMedium,
        modifier = modifier.padding(horizontal = 16.dp),
    )
}

@Composable
fun TextTitleSmall(text: String, modifier: Modifier = Modifier, color: Color = MaterialTheme.colorScheme.onBackground) {
    AuxText(
        text = text,
        style = MaterialTheme.typography.titleSmall,
        color = color,
        modifier = modifier.padding(horizontal = 16.dp),
    )
}

@Composable
internal fun AuxText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    color: Color = MaterialTheme.colorScheme.onBackground,
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        style = style,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}