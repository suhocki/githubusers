package com.suhocki.githubusers.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.suhocki.githubusers.ui.userdetails.UserDetailsScreen
import com.suhocki.githubusers.ui.userdetails.UserDetailsViewModel
import com.suhocki.githubusers.ui.users.UsersScreen
import com.suhocki.githubusers.ui.users.UsersViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun NavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Direction.Users
    ) {
        composable<Direction.Users> {
            val viewModel = koinViewModel<UsersViewModel>()
            UsersScreen(
                users = viewModel.users,
                error = viewModel.error,
                onClick = { userId -> navController.navigate(Direction.UserDetails(userId)) },
                onListEndReached = viewModel::loadNextPage
            )
        }
        composable<Direction.UserDetails> { entry ->
            val args = entry.toRoute<Direction.UserDetails>()
            val viewModel = koinViewModel<UserDetailsViewModel> { parametersOf(args.url) }
            UserDetailsScreen(
                userDetails = viewModel.userDetails,
                message = viewModel.message,
            )
        }
    }
}