package com.example.koinstructure.ui.fragment.detailTeam.adapter

import androidx.lifecycle.MutableLiveData
import com.example.koinstructure.data.model.Player

class PlayerItemViewModel(var movie: Player) {

    var imageUrl = MutableLiveData<String?>()

    init {
        imageUrl.value = movie.image_path
    }



}