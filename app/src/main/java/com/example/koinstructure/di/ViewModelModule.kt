package com.example.koinstructure.di

import com.example.koinstructure.ui.activity.main.MainViewModel
import com.example.koinstructure.ui.activity.splash.SplashViewModel
import com.example.koinstructure.ui.fragment.detailTeam.DetailTeamViewModel
import com.example.koinstructure.ui.fragment.home.HomeViewModel
import com.example.koinstructure.ui.fragment.profile.ProfileViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val baseViewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { SplashViewModel(get()) }
    viewModel { HomeViewModel(get(),get(),get()) }
    viewModel { DetailTeamViewModel(get(),get(),get()) }
    viewModel { ProfileViewModel(get()) }

}