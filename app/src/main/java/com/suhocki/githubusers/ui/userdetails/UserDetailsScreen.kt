package com.suhocki.githubusers.ui.userdetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.suhocki.githubusers.domain.userdetails.UserDetails
import kotlinx.coroutines.flow.Flow

@Composable
fun UserDetailsScreen(
    userDetails: Flow<UserDetails>,
    message: Flow<String?>,
) {
    val userDetails by userDetails.collectAsState(null)
    val message by message.collectAsState(null)

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(message) {
        message?.let { message ->
            snackbarHostState.showSnackbar(message)
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { padding ->
        if (userDetails == null) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        } else userDetails?.let { details ->
            Column(
                modifier = Modifier.fillMaxSize().padding(padding),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    modifier = Modifier
                        .padding(48.dp)
                        .heightIn(max = 140.dp)
                        .clip(shape = RoundedCornerShape(8.dp)),
                    model = details.avatarUrl,
                    contentDescription = null,
                )
                listOf(
                    "Name" to details.name,
                    "Login" to details.login,
                    "Companies" to details.company,
                    "Blog" to details.blog,
                    "Location" to details.location,
                    "Email" to details.email,
                    "Bio" to details.bio,
                    "Twitter" to details.twitterUsername,
                ).filterNot { (_, value) ->
                    value.isNullOrBlank()
                }.forEach { (key, value) ->
                    Text(
                        text = "$key: $value",
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
