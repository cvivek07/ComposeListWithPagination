package com.vivekchoudhary.listapp.view.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.vivekchoudhary.listapp.R
import com.vivekchoudhary.listapp.repository.model.Post

@Composable
fun DetailScreen(post: Post) {
    val mediumPadding = dimensionResource(R.dimen.padding_medium)
    Column(
        verticalArrangement = Arrangement.spacedBy(mediumPadding),
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.padding(mediumPadding)
    ) {
        Text(
            text = post.id.toString(),
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = post.title,
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = post.body,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}