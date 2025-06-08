package com.agile.ktorptoject.ui.home.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agile.domain.model.User
import com.agile.domain.usecase.GetUsersUseCase
import com.agile.domain.usecase.RefreshUsersUseCase
import com.agile.ktorptoject.ui.home.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val refreshUsersUseCase: RefreshUsersUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState<List<User>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<User>>> = _uiState

    init {
        fetchUsers()
    }

    fun fetchUsers() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val users = getUsersUseCase()
                _uiState.value = UiState.Success(users)
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Failed to fetch users: ${e.message}")
            }
        }
    }


    fun refreshUsers() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val users = refreshUsersUseCase()
                _uiState.value = UiState.Success(users)
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Failed to refresh users: ${e.message}")
            }
        }
    }
}