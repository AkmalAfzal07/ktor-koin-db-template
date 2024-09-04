package com.example.koinstructure.di

import android.content.Context
import androidx.room.Room
import com.example.koinstructure.data.localDb.LocalDb
import com.example.koinstructure.data.localDb.dao.PlayerDao
import com.example.koinstructure.data.localDb.dao.TeamDao
import com.example.koinstructure.utils.AppConstant
import org.koin.dsl.module


internal fun provideDb(context: Context): LocalDb {
    return Room.databaseBuilder(context, LocalDb::class.java, AppConstant.DB_NAME)
        .fallbackToDestructiveMigration()
        .build()
}

internal fun providePlayerDao(db: LocalDb):PlayerDao = db.playerDao()
internal fun provideTeamDao(db: LocalDb):TeamDao = db.teamDao()

val databaseModule = module {
    single { provideDb(get()) }
    single { providePlayerDao(get()) }
    single { provideTeamDao(get()) }
}