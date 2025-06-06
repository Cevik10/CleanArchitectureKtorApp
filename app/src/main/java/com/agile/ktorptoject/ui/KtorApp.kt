package com.agile.ktorptoject.ui

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.agile.ktorptoject.ui.home.user.UserScreen
import com.agile.ktorptoject.ui.home.userdetail.UserDetailScreen

@Composable
fun KtorApp(
    appState: AppState = rememberAppState()
) {
    NavHost(
        navController = appState.navController,
        startDestination = Screen.UserList.route,
        popExitTransition = { scaleOut(targetScale = 0.9f) },
        popEnterTransition = { EnterTransition.None }
    ) {
        composable(Screen.UserList.route) { backStackEntry ->
            UserScreen(
                onNavigateToUserDetail = { userId ->
                    appState.navigateToUserDetail(userId, backStackEntry)
                }
            )
        }

        composable(
            route = Screen.UserDetail.route,
            arguments = listOf(navArgument(Screen.USER_ID_ARG) {
                type = NavType.IntType
            })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt(Screen.USER_ID_ARG)
                ?: return@composable
            UserDetailScreen(userId = userId)
        }
    }
}