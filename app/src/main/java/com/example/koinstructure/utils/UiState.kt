package com.example.koinstructure.utils

sealed class UiState<out T> {

    data class Success<T>(val data: T?) : UiState<T>()

    data class Error<T>(val message: String) : UiState<T>()

    object Idle : UiState<Nothing>()
    object Loading : UiState<Nothing>()

}