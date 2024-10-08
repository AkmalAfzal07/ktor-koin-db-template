package com.example.koinstructure.ui.fragment.home

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.example.koinstructure.data.localDb.dao.TeamDao
import com.example.koinstructure.data.model.ResponseTeam
import com.example.koinstructure.data.model.Team
import com.example.koinstructure.data.repository.Repository
import com.example.koinstructure.ui.base.BaseViewModel
import com.example.koinstructure.utils.UiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Named

class HomeViewModel(application: Application,private val repository: Repository, private val teamDao: TeamDao, @Named("IO") private val ioDispatcher: CoroutineDispatcher) : BaseViewModel(application) {

    private val _uiStateTeamList = MutableStateFlow<UiState<ResponseTeam?>>(UiState.Idle)
    val uiStateTeamList: StateFlow<UiState<ResponseTeam?>> = _uiStateTeamList.asStateFlow()

    fun getTeamList() = viewModelScope.launch {
        fetchData(_uiStateTeamList) { repository.getTeamsData(context) }
    }

   suspend fun insertTeams(data: MutableList<Team>) = viewModelScope.launch {
       teamDao.insertAll(data)
       teamDao.updateData()
    }

    suspend fun fetchDataFromDatabase(): Flow<List<Team>> {
        return withContext(ioDispatcher) {
            teamDao.fetchAllData()
        }
    }
}