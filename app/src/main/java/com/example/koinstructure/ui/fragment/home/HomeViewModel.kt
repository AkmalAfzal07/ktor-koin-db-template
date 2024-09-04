package com.example.koinstructure.ui.fragment.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.koinstructure.data.localDb.dao.TeamDao
import com.example.koinstructure.data.model.ResponseTeam
import com.example.koinstructure.data.model.Team
import com.example.koinstructure.data.repository.Repository
import com.example.koinstructure.ui.base.BaseViewModel
import com.example.koinstructure.utils.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(application: Application,val repository: Repository,val teamDao: TeamDao) : BaseViewModel(application) {

    val _uiStateTeamList = MutableStateFlow<UiState<ResponseTeam?>>(UiState.Idle)
    val uiStateTeamList: StateFlow<UiState<ResponseTeam?>> = _uiStateTeamList.asStateFlow()

    fun getTeamList() = viewModelScope.launch {
        fetchData(_uiStateTeamList) { repository.getTeamsData(context) }
    }

   suspend fun insertTeams(data: MutableList<Team>) {
       return withContext(Dispatchers.IO){
           teamDao.insertAll(data)
           teamDao.updateData()
       }
    }

    suspend fun fetchDataFromDatabase(): Flow<List<Team>> {
        return withContext(Dispatchers.IO) {
            teamDao.fetchAllData()
        }
    }
}