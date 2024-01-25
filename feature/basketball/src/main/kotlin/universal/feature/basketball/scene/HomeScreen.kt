package universal.feature.basketball.scene

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
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
import universal.feature.basketball.model.Player
import org.koin.androidx.compose.koinViewModel
import universal.design.compose.component.ItemPlayer

@Composable
internal fun HomeScreen(viewModel: HomeViewModel = koinViewModel()) {
    val players: LazyPagingItems<Player> = viewModel.moviesState.collectAsLazyPagingItems()
    Screen(
        state = players,
        onBack = viewModel::onBack,
        onPlayer = viewModel::onPlayer,
    )
}

@Composable
private fun Screen(
    state: LazyPagingItems<Player>,
    onBack: () -> Unit,
    onPlayer: (Player) -> Unit,
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
    pagingItems: LazyPagingItems<Player>,
    innerPadding: PaddingValues,
    onPlayer: (Player) -> Unit,
) {
    LazyColumn(modifier = Modifier.padding(innerPadding)) {
        item { Spacer(modifier = Modifier.padding(4.dp)) }

        items(pagingItems.itemCount) { index ->
            pagingItems[index]?.let { pagingItem ->
                ItemPlayer(
                    title = "${pagingItem.firstName} ${pagingItem.lastName}",
                    description = pagingItem.team.city + " ${pagingItem.team.name}",
                    imageUrl = "https://picsum.photos/200?random=" + "${pagingItem.id}",
                ) {
                    onPlayer(pagingItem)
                }
            }
        }
        pagingItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { Text(text = "loading") }
                }

                loadState.append is LoadState.Loading -> {
                    item { Text(text = "loading next page") }
                }

                loadState.refresh is LoadState.Error -> {
                    val error = pagingItems.loadState.refresh as LoadState.Error
                    item { ErrorContent(refresh = { retry() }) }
                }

                loadState.append is LoadState.Error -> {
                    val error = pagingItems.loadState.append as LoadState.Error
                    item { ErrorContent(refresh = { retry() }) }
                }
            }
        }
        item { Spacer(modifier = Modifier.padding(4.dp)) }
    }
}

@Composable
private fun ErrorContent(refresh: () -> Unit) {
    Text(text = "Content Error")
    Spacer(modifier = Modifier.size(16.dp))
    Button(onClick = refresh) {
        Text(text = "Retry")
    }
}