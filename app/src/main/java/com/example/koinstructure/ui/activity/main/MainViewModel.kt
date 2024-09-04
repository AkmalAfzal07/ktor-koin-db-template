package com.example.koinstructure.ui.activity.main

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import com.example.koinstructure.data.repository.Repository
import com.example.koinstructure.ui.base.BaseViewModel

class MainViewModel(application: Application) : BaseViewModel(application) {

    var showProgress = mutableStateOf(false)
}