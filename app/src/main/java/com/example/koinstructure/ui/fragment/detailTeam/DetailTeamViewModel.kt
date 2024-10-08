package com.example.koinstructure.ui.fragment.detailTeam

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.example.koinstructure.data.localDb.dao.PlayerDao
import com.example.koinstructure.data.model.Player
import com.example.koinstructure.data.model.ResponsePlayers
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

class DetailTeamViewModel(application: Application,private val repository: Repository, private val playerDao: PlayerDao,@Named("IO") private val ioDispatcher: CoroutineDispatcher) : BaseViewModel(application) {
    private val _uiStatePlayerList = MutableStateFlow<UiState<ResponsePlayers?>>(UiState.Idle)
    val uiStatePlayerDetailList: StateFlow<UiState<ResponsePlayers?>> = _uiStatePlayerList.asStateFlow()

        fun fetchFromRemote() = viewModelScope.launch {
        val isData = playerDao.totalPlayerRecords()
        if (isData == 0) {
            fetchData(_uiStatePlayerList) { repository.getPlayersData(context) }
        }
    }

    fun insertPlayerData(data: MutableList<Player>) = viewModelScope.launch {
        playerDao.insertAll(data)
    }

    suspend fun fetchDataFromDatabase(id: Int): Flow<List<Player>> {
        return withContext(ioDispatcher) {
            playerDao.fetchAllData(id)
        }
    }
}