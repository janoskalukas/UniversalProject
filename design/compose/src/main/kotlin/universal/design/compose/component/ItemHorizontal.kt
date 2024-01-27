package universal.design.compose.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import universal.design.compose.theme.PreviewTheme

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
public fun ItemHorizontal(
    title: String,
    label: String,
    description: String? = null,
    imageUrl: String,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
    ) {
        Row {
            GlideImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier.size(120.dp),
            )
            Column(modifier = Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier.size(8.dp))
                TextTitleSmall(text = title)
                Spacer(modifier = Modifier.size(4.dp))
                TextBodySmall(text = label)
                Spacer(modifier = Modifier.size(4.dp))
                description?.let { TextBodySmall(text = it) }
                Spacer(modifier = Modifier.size(4.dp))
            }
        }
    }
}

@Preview(locale = "en", name = "Day Mode")
@Composable
private fun ScreenPreviewLight() = PreviewTheme {
    ItemHorizontal(title = "Title", label = "Label", description = "description", imageUrl = "", onClick = { })
}