package com.example.koinstructure.data.repository.remote

import com.example.koinstructure.data.model.ResponsePlayers
import com.example.koinstructure.data.model.ResponseTeam
import com.example.koinstructure.utils.AppConstant
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ApiService(val client :HttpClient)  {

    suspend fun getTeamData() = client.get(AppConstant.TEAM_URL).body<ResponseTeam>()
    suspend fun getPlayerData() = client.get(AppConstant.PLAYER_URL).body<ResponsePlayers>()

}