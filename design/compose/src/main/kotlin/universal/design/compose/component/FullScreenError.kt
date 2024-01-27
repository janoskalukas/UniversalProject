package universal.design.compose.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import universal.library.localisation.infrastructure.string

@Composable
public fun FullScreenError(refresh: () -> Unit) {
    Scaffold(
        bottomBar = { AuxButton(onClick = refresh, modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp)) },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(text = stringResource(string.something_went_wrong))
            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}