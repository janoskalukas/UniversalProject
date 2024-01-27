package universal.feature.basketball.scene

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import org.koin.androidx.compose.koinViewModel
import universal.design.compose.component.AuxButton
import universal.design.compose.component.FullScreenError
import universal.design.compose.component.FullScreenSpinner
import universal.design.compose.component.ItemPlayer
import universal.design.compose.component.Spinner
import universal.feature.basketball.presentation.PlayerState
import universal.library.localisation.infrastructure.string

@Composable
internal fun PlayersListScreen(viewModel: PlayersListViewModel = koinViewModel()) {
    val players: LazyPagingItems<PlayerState> = viewModel.playersState.collectAsLazyPagingItems()
    Screen(
        state = players,
        onBack = viewModel::onBack,
        onPlayer = viewModel::onPlayer,
    )
}

@Composable
private fun Screen(
    state: LazyPagingItems<PlayerState>,
    onBack: () -> Unit,
    onPlayer: (PlayerState) -> Unit,
) {
    BackHandler(enabled = true, onBack = onBack)
    Scaffold(
        backgroundColor = MaterialTheme.colorScheme.background,
    ) { paddingValues ->
        Content(
            pagingItems = state,
            innerPadding = paddingValues,
            onPlayer = onPlayer,
        )
    }
}

@Composable
private fun Content(
    pagingItems: LazyPagingItems<PlayerState>,
    innerPadding: PaddingValues,
    onPlayer: (PlayerState) -> Unit,
) {
    Column {
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            item { Spacer(modifier = Modifier.size(4.dp)) }

            items(pagingItems.itemCount) { index ->
                pagingItems[index]?.let { pagingItem ->
                    ItemPlayer(
                        name = pagingItem.fullName,
                        team = pagingItem.team,
                        position = pagingItem.position,
                        imageUrl = pagingItem.imageUrl,
                    ) {
                        onPlayer(pagingItem)
                    }
                }
            }
            when (pagingItems.loadState.append) {
                is LoadState.Loading -> item { Spinner(modifier = Modifier.fillMaxWidth()) }
                is LoadState.Error -> item { ErrorContent(refresh = { pagingItems.retry() }) }
                is LoadState.NotLoading -> Unit
            }
            item { Spacer(modifier = Modifier.size(8.dp)) }
        }
        Refresh(pagingItems)
    }
}

@Composable
private fun Refresh(pagingItems: LazyPagingItems<PlayerState>) {
    when (pagingItems.loadState.refresh) {
        is LoadState.Loading -> FullScreenSpinner()
        is LoadState.Error -> FullScreenError(refresh = { pagingItems.retry() })
        is LoadState.NotLoading -> Unit
    }
}

@Composable
private fun ErrorContent(refresh: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = stringResource(string.something_went_wrong))
        Spacer(modifier = Modifier.size(16.dp))
        AuxButton(onClick = refresh)
    }
}