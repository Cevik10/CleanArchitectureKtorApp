package com.agile.ktorptoject.ui.home.userdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agile.domain.model.User
import com.agile.domain.usecase.GetUserByIdUseCase
import com.agile.ktorptoject.ui.home.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val getUserByIdUseCase: GetUserByIdUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow<UiState<User>>(UiState.Loading)
    val uiState: StateFlow<UiState<User>> = _uiState

    fun fetchUser(id: Int) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val user = getUserByIdUseCase(id)
                _uiState.value = UiState.Success(user)
            } catch (e: Exception) {
                _uiState.value = UiState.Error("Failed to fetch user: ${e.message}")
            }
        }
    }
}