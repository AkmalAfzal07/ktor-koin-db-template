package com.example.koinstructure.data.repository.remote

import android.content.Context
import com.example.koinstructure.utils.AppConstant
import com.example.koinstructure.utils.UiState
import com.example.koinstructure.utils.Utils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


fun <T> toResultFlow(context: Context, call: suspend () -> T?) : Flow<UiState<T?>> {
    return flow<UiState<T?>> {
        if(Utils.hasInternetConnection(context)) {
            emit(UiState.Loading)
            val c = call.invoke()
            c?.let { response ->
                try {
                    emit(UiState.Success(response))
                } catch (e: Exception) {
                    emit(UiState.Error(e.toString()))
                }
            }
        }else{
            emit(UiState.Error(AppConstant.API_INTERNET_MESSAGE))
        }
    }.flowOn(Dispatchers.IO)
}