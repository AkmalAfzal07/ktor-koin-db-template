package com.example.koinstructure.di

import com.example.koinstructure.ui.fragment.detailTeam.adapter.PlayerAdapter
import com.example.koinstructure.ui.fragment.home.adapter.TeamAdapter
import org.koin.dsl.module
import kotlin.math.sin

/**
 * @Author: Muhammad Akmal
 * @Date: 04-09-2024
 */

 val adaptersModule = module {
     single { TeamAdapter(arrayListOf()) }
     factory { PlayerAdapter(arrayListOf()) }
 }

