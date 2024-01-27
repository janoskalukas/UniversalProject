package universal.design.compose.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
public fun ButtonOutlined(
    text: String,
    modifier: Modifier,
    onClick: () -> Unit,
) {
    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
    ) {
        AuxText(text = text, color = MaterialTheme.colorScheme.tertiary)
    }
}

@Composable
public fun Button(text: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiary),
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(4.dp),
            color = Color.White,
            style = MaterialTheme.typography.titleMedium,
        )
    }
}