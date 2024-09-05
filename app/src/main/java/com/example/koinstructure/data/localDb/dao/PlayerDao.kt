package com.example.koinstructure.data.localDb.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.koinstructure.data.model.Player
import kotlinx.coroutines.flow.Flow

@Dao
interface PlayerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<Player>)

    @Query("SELECT * FROM player WHERE country_id = :id")
    fun fetchAllData(id: Int): Flow<List<Player>>

    @Query("SELECT * FROM player")
    fun getAllData(): Flow<List<Player>>


    @Query("SELECT count(*) FROM player")
    suspend fun totalPlayerRecords(): Int
}