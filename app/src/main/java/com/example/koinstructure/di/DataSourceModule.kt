package com.example.koinstructure.di

import com.example.koinstructure.data.repository.remote.RemoteDataSource
import org.koin.dsl.module

/**
 * @Author: Muhammad Akmal
 * @Date: 04-09-2024
 */

val remoteDataSourceModule= module {
    factory {  RemoteDataSource(get()) }
}