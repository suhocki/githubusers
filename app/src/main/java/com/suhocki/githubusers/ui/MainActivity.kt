package com.suhocki.githubusers.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.suhocki.githubusers.ui.navigation.NavGraph
import com.suhocki.githubusers.ui.theme.GithubUsersTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubUsersTheme {
                val navController = rememberNavController()
                NavGraph(navController)
            }
        }
    }
}
