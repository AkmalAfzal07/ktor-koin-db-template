package com.example.koinstructure.data.localDb.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.koinstructure.data.model.Team
import kotlinx.coroutines.flow.Flow

@Dao
interface TeamDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<Team>)

    @Query("SELECT * FROM team")
    fun fetchAllData(): Flow<List<Team>>

    @Query("SELECT count(*) from team")
    suspend fun getSize():Int


    @Transaction
    suspend fun updateData() {
        // this is just for data update because of random country id in response
        // we can use common method for update and pass parameter of id and code intent doing this.
        updateIndia()
        updatePak()
        updateAus()
        updateBGB()
        updateENG()
        updateLNK()
        updateZAF()
        updateNZL()
        updateWI()
        updateZIm()
    }

    @Query("UPDATE team set country_id = 2325 where code like 'ZIM'")
    suspend fun updateZIm()

    @Query("UPDATE team set country_id = 24150873 where code like 'WI'")
    suspend fun updateWI()

    @Query("UPDATE team set country_id = 2817 where code like 'NZL'")
    suspend fun updateNZL()

    @Query("UPDATE team set country_id = 146 where code like 'ZAF'")
    suspend fun updateZAF()

    @Query(" UPDATE team set country_id = 38404 where code like 'LKA'")
    suspend fun updateLNK()

    @Query("UPDATE team set country_id = 462 where code like 'ENG'")
    suspend fun updateENG()

    @Query("UPDATE team set country_id = 155043 where code like 'BGD'")
    suspend fun updateBGB()

    @Query("UPDATE team set country_id = 98 where code like 'AUS'")
    suspend fun updateAus()

    @Query("UPDATE team set country_id = 52126 where code like 'PAK'")
    suspend fun updatePak()

    @Query("Update team set country_id=153732 where code like 'IND'")
    suspend fun updateIndia()


}