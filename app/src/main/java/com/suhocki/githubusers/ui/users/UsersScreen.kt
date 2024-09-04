package com.suhocki.githubusers.ui.users

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.suhocki.githubusers.domain.user.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun UsersScreen(
    users: StateFlow<List<User>?>,
    error: Flow<Throwable?>,
    onClick: (String) -> Unit,
    onListEndReached: () -> Unit,
) {
    val users by users.collectAsState()
    val error by error.collectAsState(null)

    val snackbarHostState = remember { SnackbarHostState() }
    val lazyListState = rememberLazyListState()

    val isLastUserVisible by remember {
        derivedStateOf {
            val item = lazyListState.layoutInfo.visibleItemsInfo
                .lastOrNull() ?: return@derivedStateOf false
            val users = users ?: return@derivedStateOf false
            item.index >= users.lastIndex
        }
    }

    LaunchedEffect(isLastUserVisible) {
        if (isLastUserVisible) {
            onListEndReached()
        }
    }

    LaunchedEffect(error) {
        error?.localizedMessage?.let { message ->
            snackbarHostState.showSnackbar(message)
        }
    }

    val activity = LocalContext.current as? Activity

    BackHandler {
        activity?.finish()
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding),
            contentPadding = PaddingValues(vertical = 8.dp),
            state = lazyListState,
        ) {
            users?.let { users ->
                items(users, key = User::id) { user ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onClick(user.url) }
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(shape = RoundedCornerShape(8.dp)),
                            model = user.avatarUrl,
                            contentDescription = null,
                        )
                        Text(
                            modifier = Modifier.align(Alignment.CenterVertically),
                            text = user.login,
                        )
                    }
                }
            }
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp).run {
                            if (users.orEmpty().isEmpty()) {
                                fillParentMaxHeight()
                            } else {
                                wrapContentHeight()
                            }
                        }
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

