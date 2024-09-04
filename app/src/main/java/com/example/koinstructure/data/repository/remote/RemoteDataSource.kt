package com.example.koinstructure.data.repository.remote

class RemoteDataSource(val apiService: ApiService) {
    suspend fun getTeamData() = apiService.getTeamData()
    suspend fun getPlayerData() = apiService.getPlayerData()

}