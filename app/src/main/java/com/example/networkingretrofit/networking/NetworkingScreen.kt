package com.example.networkingretrofit.networking

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NetworkingScreen(
    networkingViewModel: NetworkingViewModel = hiltViewModel()
) {
    val posts: PostScreenUIState by remember { networkingViewModel.posts }.collectAsState()

    LaunchedEffect(key1 = Unit) {
        networkingViewModel.getPosts()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "NetworkingScreen") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (posts) {
                is PostScreenUIState.Initial -> {}

                is PostScreenUIState.Loading -> {
                    LoadingIndicator()
                }

                is PostScreenUIState.Success -> {
                    PostsList(
                        modifier = Modifier.fillMaxSize(),
                        posts = (posts as PostScreenUIState.Success).posts,
                        onCreatePost = { post ->
                            networkingViewModel.createPost(post = post)
                        },
                        onDeletePost = { postId ->
                            networkingViewModel.deletePost(postId = postId)
                        }
                    )
                }

                is PostScreenUIState.Error -> {
                    ErrorText(
                        modifier = Modifier.fillMaxSize(),
                        text = (posts as PostScreenUIState.Error).message
                    )
                }
            }

//            Button(onClick = { networkingViewModel.getPosts() }) {
//                Text(text = "Get Posts")
//            }
        }
    }
}

@Composable
fun PostsList(
    modifier: Modifier = Modifier,
    posts: List<PostModel>,
    onCreatePost: (PostModel) -> Unit,
    onDeletePost: (postId: Int) -> Unit,
) {
    Column(modifier = modifier) {
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(posts) { post ->
                PostItem(
                    modifier = Modifier.fillMaxWidth(),
                    post = post
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = { onCreatePost(dummyPost) }) {
                Text(text = "Create post")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = { onDeletePost(dummyPost.id) }) {
                Text(text = "Delete post")
            }
        }
    }
}

@Composable
fun PostItem(
    modifier: Modifier = Modifier,
    post: PostModel
) {
    Card(modifier = modifier) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = "Post ID: ${post.id}",
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "User ID: ${post.userId}",
                    fontWeight = FontWeight.SemiBold
                )
            }
            Spacer(modifier = Modifier.height(4.dp))

            Text(text = "Title", fontWeight = FontWeight.SemiBold)
            Text(text = post.title)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "Description", fontWeight = FontWeight.SemiBold)
            Text(text = post.body, maxLines = 2, overflow = TextOverflow.Ellipsis)
        }
    }
}

@Preview
@Composable
private fun PostItemPreview() {
    PostItem(
        modifier = Modifier.fillMaxWidth(),
        post = dummyPost
    )
}

val dummyPost = PostModel(
    userId = 1,
    id = 1,
    title = "Sample post title",
    body = "Sample post body"
)