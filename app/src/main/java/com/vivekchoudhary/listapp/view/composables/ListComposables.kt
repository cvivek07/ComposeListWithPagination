package com.vivekchoudhary.listapp.view.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import com.vivekchoudhary.listapp.R
import com.vivekchoudhary.listapp.repository.model.Post
import com.vivekchoudhary.listapp.viewmodel.PostsViewModel
import com.vivekchoudhary.listapp.viewmodel.ScreenState

@Composable
fun ListScreen(viewModel: PostsViewModel, onItemClicked: (Post) -> Unit) {
    val mediumPadding = dimensionResource(R.dimen.padding_medium)
    val state = viewModel.state
    when (state.error) {
        is Throwable -> ErrorMessage(throwable = state.error)
        else -> ListComposable(state, mediumPadding, viewModel, onItemClicked)
    }
}

@Composable
private fun ListComposable(
    state: ScreenState,
    mediumPadding: Dp,
    viewModel: PostsViewModel,
    onItemClicked: (Post) -> Unit
) {
    val listState = rememberLazyListState()
    val list = state.items
    LazyColumn {
        val size = list.size
        items(size) { index ->
            val post = list[index]
            LaunchedEffect(key1 = listState) {
                if (index >= size - 1 && !state.endReached && !state.isLoading) {
                    viewModel.fetchPosts()
                }
            }
            CardRow(mediumPadding, post, onItemClicked)
        }
        item {
            if (state.isLoading) {
                Loader()
            }
        }
    }
}

@Composable
private fun CardRow(
    mediumPadding: Dp,
    post: Post,
    onItemClicked: (Post) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(mediumPadding)
            .clickable {
                onItemClicked(post)
            },
        elevation = CardDefaults.cardElevation(defaultElevation = dimensionResource(R.dimen.card_elevation))
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(mediumPadding),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(mediumPadding)
        ) {
            Text(
                text = post.id.toString(),
                style = typography.titleMedium
            )
            Text(
                text = post.title,
                style = typography.bodyMedium
            )
        }
    }
}