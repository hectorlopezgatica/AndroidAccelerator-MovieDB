package com.hlopezg.presentation_common.state

sealed class UiState<out T: Any> {
    data class Loading(val type: LoadingType) : UiState<Nothing>()
    data class Error(val errorMessage: String): UiState<Nothing>()
    data class Success<T: Any>(val data: T): UiState<T>()
}

sealed class LoadingType{
    data object RowList: LoadingType()
    data object FullScreen: LoadingType()
    data object ContentDetail: LoadingType()
}