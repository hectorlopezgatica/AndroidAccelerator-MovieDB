package com.hlopezg.presentation_common.state

sealed class UiState<out T: Any> {
    object Loading : UiState<Nothing>()
    data class Error(val errorMessage: String): UiState<Nothing>()
    data class Success<T: Any>(val date: T): UiState<T>()
}