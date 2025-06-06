package com.agile.ktorptoject.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

sealed class Screen(val route: String) {
    object UserList : Screen("user_list")

    object UserDetail : Screen("user_detail/{$USER_ID_ARG}") {
        fun createRoute(userId: Int) = "user_detail/$userId"
    }

    companion object {
        const val USER_ID_ARG = "userId"
    }
}

class AppState(
    val navController: NavHostController
) {
    fun navigateToUserDetail(userId: Int, from: NavBackStackEntry) {
        if (from.lifecycleIsResumed()) {
            navController.navigate(Screen.UserDetail.createRoute(userId))
        }
    }

    fun navigateBack() {
        navController.popBackStack()
    }
}

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController()
): AppState = remember(navController) {
    AppState(navController)
}

fun NavBackStackEntry.lifecycleIsResumed(): Boolean {
    return this.lifecycle.currentState == Lifecycle.State.RESUMED
}
