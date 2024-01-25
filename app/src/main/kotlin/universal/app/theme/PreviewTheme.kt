package universal.app.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
public fun PreviewTheme(
    modifier: Modifier = Modifier,
    backgroundProvider: @Composable () -> Color = { MaterialTheme.colorScheme.background },
    content: @Composable ColumnScope.() -> Unit,
) {
    UniversalTheme {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundProvider())
                .then(modifier),
            content = content,
        )
    }
}