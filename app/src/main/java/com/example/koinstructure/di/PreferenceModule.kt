package com.example.koinstructure.di

import com.example.koinstructure.data.preference.AppPreferences
import org.koin.dsl.module

val preferenceModule = module {
    single {
        AppPreferences(get())
    }
}