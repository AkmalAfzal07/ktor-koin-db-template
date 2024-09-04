package com.example.koinstructure

import android.app.Application
import com.example.koinstructure.di.adaptersModule
import com.example.koinstructure.di.apiServiceModule
import com.example.koinstructure.di.baseViewModelModule
import com.example.koinstructure.di.databaseModule
import com.example.koinstructure.di.networkModule
import com.example.koinstructure.di.preferenceModule
import com.example.koinstructure.di.remoteDataSourceModule
import com.example.koinstructure.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.logger.slf4jLogger

class MyApplication:Application() {


    override fun onCreate() {
        super.onCreate()

        // start koin dependency injection
        startKoin {
            androidContext(this@MyApplication)
            slf4jLogger(Level.DEBUG)
            modules(
                databaseModule,
                networkModule,
                apiServiceModule,
                remoteDataSourceModule,
                repositoryModule,
                baseViewModelModule,
                preferenceModule,
                adaptersModule
            )
        }
    }


}