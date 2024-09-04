package com.example.koinstructure.ui.fragment.home.adapter

import androidx.lifecycle.MutableLiveData
import com.example.koinstructure.data.model.Team

class TeamItemViewModel(var movie: Team, var mListener: MovieItemViewModelListener) {

    var imageUrl = MutableLiveData<String?>()

    init {
        imageUrl.value = movie.image_path
    }

    fun onItemClick() {
        mListener.onItemClick(movie)
    }


    interface MovieItemViewModelListener {
        fun onItemClick(movie: Team)
    }
}