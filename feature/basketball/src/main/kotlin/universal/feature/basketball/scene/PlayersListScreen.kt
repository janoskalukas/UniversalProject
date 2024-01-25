package universal.feature.basketball.scene

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import org.koin.androidx.compose.koinViewModel
import universal.design.compose.component.FullScreenSpinner
import universal.design.compose.component.ItemPlayer
import universal.design.compose.component.Spinner
import universal.feature.basketball.scene.PlayersListViewModel.PlayerState

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
            item { Spacer(modifier = Modifier.padding(4.dp)) }

            items(pagingItems.itemCount) { index ->
                pagingItems[index]?.let { pagingItem ->
                    ItemPlayer(
                        name = pagingItem.fullName,
                        description = pagingItem.description,
                        imageUrl = pagingItem.imageUrl,
                    ) {
                        onPlayer(pagingItem)
                    }
                }
            }
            when (pagingItems.loadState.append) {
                is LoadState.Loading -> item {
                    Spinner(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(all = 8.dp),
                    )
                }

                is LoadState.Error -> {
                    val error = pagingItems.loadState.append as LoadState.Error; item { ErrorContent(refresh = { pagingItems.retry() }) }
                }

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
        is LoadState.Error -> {
            val error = pagingItems.loadState.refresh as LoadState.Error; ErrorContent(refresh = { pagingItems.retry() })
        }

        is LoadState.NotLoading -> Unit
    }
}

@Composable
private fun ErrorContent(refresh: () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Content Error")
        Spacer(modifier = Modifier.size(16.dp))
        Button(onClick = refresh) {
            Text(text = "Retry")
        }
    }
}