package com.example.koinstructure.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.qualifier.named
import org.koin.dsl.module


internal fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

internal fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default

internal fun providesCoroutineScope(dispatcher: CoroutineDispatcher): CoroutineScope = CoroutineScope(SupervisorJob() + dispatcher)

val coroutineModule = module {
    single (named("IO")){ providesIODispatcher() }
    single (named("Default")){ providesDefaultDispatcher() }
    single (named("IOCoroutineScope")){ providesCoroutineScope(get(named("IO"))) }
}
