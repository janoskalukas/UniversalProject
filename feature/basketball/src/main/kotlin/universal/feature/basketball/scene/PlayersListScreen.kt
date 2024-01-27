package universal.feature.basketball.scene

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import org.koin.androidx.compose.koinViewModel
import universal.design.compose.component.Button
import universal.design.compose.component.FullScreenError
import universal.design.compose.component.FullScreenSpinner
import universal.design.compose.component.ItemHorizontal
import universal.design.compose.component.Spinner
import universal.design.compose.component.TextTitleMedium
import universal.feature.basketball.presentation.PlayerListItemState
import universal.library.localisation.infrastructure.string

@Composable
internal fun PlayersListScreen(viewModel: PlayersListViewModel = koinViewModel()) {
    Screen(
        state = viewModel.playersState.collectAsLazyPagingItems(),
        onPlayer = viewModel::onPlayer,
    )
}

@Composable
private fun Screen(
    state: LazyPagingItems<PlayerListItemState>,
    onPlayer: (PlayerListItemState) -> Unit,
) {
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
    pagingItems: LazyPagingItems<PlayerListItemState>,
    innerPadding: PaddingValues,
    onPlayer: (PlayerListItemState) -> Unit,
) {
    Column {
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            item { Spacer(modifier = Modifier.size(4.dp)) }

            items(pagingItems.itemCount) { index ->
                pagingItems[index]?.let { pagingItem ->
                    ItemHorizontal(
                        title = pagingItem.fullName,
                        label = pagingItem.team,
                        description = pagingItem.position,
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
private fun Refresh(pagingItems: LazyPagingItems<PlayerListItemState>) {
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
        TextTitleMedium(stringResource(string.something_went_wrong))
        Spacer(modifier = Modifier.size(16.dp))
        Button(text = stringResource(string.load_more), onClick = refresh)
    }
}