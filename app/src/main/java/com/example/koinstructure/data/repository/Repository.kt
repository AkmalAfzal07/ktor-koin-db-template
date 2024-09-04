package com.example.koinstructure.data.repository

import android.content.Context
import com.example.koinstructure.data.model.ResponsePlayers
import com.example.koinstructure.data.model.ResponseTeam
import com.example.koinstructure.data.repository.remote.RemoteDataSource
import com.example.koinstructure.data.repository.remote.toResultFlow
import com.example.koinstructure.utils.UiState
import kotlinx.coroutines.flow.Flow

class Repository(val remoteDataSource: RemoteDataSource) {

    suspend fun getTeamsData(context: Context): Flow<UiState<ResponseTeam?>> {
        return toResultFlow(context){
            remoteDataSource.getTeamData()
        }
    }

    suspend fun getPlayersData(context: Context): Flow<UiState<ResponsePlayers?>> {
        return toResultFlow(context){
            remoteDataSource.getPlayerData()
        }
    }

}