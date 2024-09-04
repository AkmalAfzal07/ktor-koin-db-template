package com.example.koinstructure.di

import com.example.koinstructure.data.repository.Repository
import org.koin.dsl.module

val repositoryModule = module {
    factory { Repository(get()) }
}