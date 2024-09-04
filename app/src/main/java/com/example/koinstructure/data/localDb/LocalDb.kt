package com.example.koinstructure.data.localDb

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.koinstructure.data.localDb.dao.PlayerDao
import com.example.koinstructure.data.localDb.dao.TeamDao
import com.example.koinstructure.data.model.Player
import com.example.koinstructure.data.model.PositionConverters
import com.example.koinstructure.data.model.Team

@Database(entities = [Player::class,Team::class], version = 1, exportSchema = false)
@TypeConverters(PositionConverters::class)
abstract class LocalDb:RoomDatabase() {
    abstract fun teamDao(): TeamDao
    abstract fun playerDao(): PlayerDao
}