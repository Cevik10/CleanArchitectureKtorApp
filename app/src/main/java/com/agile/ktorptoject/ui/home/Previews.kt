package com.agile.ktorptoject.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.agile.domain.model.User
import com.agile.ktorptoject.ui.home.user.UserScreenContent
import com.agile.ktorptoject.ui.home.userdetail.UserDetailContent


val mockUser = User(
    id = 1,
    name = "John Doe",
    email = "john.doe@example.com",
    companyName = "OpenAI Inc.",
    phone = "555-555-5555",
    website = "www.johndoe.com",
    username = "jonny"

)

@Preview(showBackground = true)
@Composable
fun PreviewUserDetailSuccess() {

    UserDetailContent(uiState = UiState.Success(mockUser), onRetry = {})
}

@Preview(showBackground = true)
@Composable
fun PreviewUserDetailLoading() {
    UserDetailContent(uiState = UiState.Loading, onRetry = {})
}

@Preview(showBackground = true)
@Composable
fun PreviewUserDetailError() {
    UserDetailContent(uiState = UiState.Error("Failed to load user"), onRetry = {})
}

@Preview(showBackground = true)
@Composable
fun PreviewUserScreenSuccess() {
    val mockUsers = listOf(
        mockUser,
        mockUser.copy(id = 2, name = "Jane Doe"),
        mockUser.copy(id = 3, name = "Bob Smith"),
        mockUser.copy(id = 4, name = "Alice Johnson"),
        mockUser.copy(id = 5, name = "Charlie Brown")
    )
    UserScreenContent(
        uiState = UiState.Success(mockUsers),
        onNavigateToUserDetail = {},
        onRetry = {},
        onRefresh = {},
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewUserScreenLoading() {
    UserScreenContent(
        uiState = UiState.Loading,
        onNavigateToUserDetail = {},
        onRetry = {},
        onRefresh = {}
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewUserScreenError() {
    UserScreenContent(
        uiState = UiState.Error("Something went wrong"),
        onNavigateToUserDetail = {},
        onRetry = {},
        onRefresh = {}
    )
}
