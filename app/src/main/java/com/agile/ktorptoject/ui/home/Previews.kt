package com.agile.ktorptoject.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.agile.domain.model.User
import com.agile.ktorptoject.ui.home.user.UserScreenContent
import com.agile.ktorptoject.ui.home.userdetail.UserDetailContent

@Preview(showBackground = true)
@Composable
fun PreviewUserDetailSuccess() {
    val mockUser = User(
        id = 1,
        name = "John Doe",
        email = "john.doe@example.com",
        companyName = "OpenAI Inc."
    )
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
        User(id = 1, name = "Alice", email = "alice@example.com", companyName = "OpenAI"),
        User(id = 2, name = "Bob", email = "bob@example.com", companyName = "Google"),
        User(id = 3, name = "Charlie", email = "charlie@example.com", companyName = "Microsoft"),
        User(id = 4, name = "David", email = "david@example.com", companyName = "Facebook"),
        User(id = 5, name = "Eve", email = "eve@example.com", companyName = "Amazon"),
        User(id = 6, name = "Frank", email = "frank@example.com", companyName = "Tesla"),
        User(id = 7, name = "Grace", email = "grace@example.com", companyName = "Uber"),
        User(id = 8, name = "Hank", email = "hank@example.com", companyName = "Airbnb"),
        User(id = 9, name = "Ivy", email = "ivy@example.com", companyName = "Netflix"),
        User(id = 10, name = "Jack", email = "jack@example.com", companyName = "Adobe"),
        User(id = 11, name = "Kelly", email = "kelly@example.com", companyName = "IBM"),
        User(id = 12, name = "Liam", email = "liam@example.com", companyName = "Salesforce"),
        User(id = 13, name = "Mia", email = "mia@example.com", companyName = "PayPal"),
        User(id = 14, name = "Noah", email = "noah@example.com", companyName = "Cisco"),
        User(id = 15, name = "Olivia", email = "olivia@example.com", companyName = "Oracle"),
        User(id = 16, name = "Peter", email = "peter@example.com", companyName = "SAP"),
        User(id = 17, name = "Quinn", email = "quinn@example.com", companyName = "Intel"),
        User(id = 18, name = "Rachel", email = "rachel@example.com", companyName = "Cisco"),
        User(id = 19, name = "Sam", email = "sam@example.com", companyName = "Salesforce"),
        User(id = 20, name = "Tina", email = "tina@example.com", companyName = "PayPal"),
        User(id = 21, name = "Ursula", email = "ursula@example.com", companyName = "Cisco"),
        User(id = 22, name = "Victor", email = "victor@example.com", companyName = "Oracle"),
    )
    UserScreenContent(
        uiState = UiState.Success(mockUsers),
        onNavigateToUserDetail = {},
        onRetry = {}
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewUserScreenLoading() {
    UserScreenContent(
        uiState = UiState.Loading,
        onNavigateToUserDetail = {},
        onRetry = {}
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewUserScreenError() {
    UserScreenContent(
        uiState = UiState.Error("Something went wrong"),
        onNavigateToUserDetail = {},
        onRetry = {}
    )
}
