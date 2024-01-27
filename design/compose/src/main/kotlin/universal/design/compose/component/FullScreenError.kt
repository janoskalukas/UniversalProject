package universal.design.compose.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import universal.library.localisation.infrastructure.string

@Composable
public fun FullScreenError(refresh: () -> Unit) {
    Scaffold(
        bottomBar = { BottomBar(refresh) },
        backgroundColor = MaterialTheme.colorScheme.background,
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            ParagraphSmall(text = stringResource(string.something_went_wrong))
        }
    }
}

@Composable
private fun BottomBar(refresh: () -> Unit) {
    Button(
        text = stringResource(string.retry),
        modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp),
        onClick = refresh,
    )
}