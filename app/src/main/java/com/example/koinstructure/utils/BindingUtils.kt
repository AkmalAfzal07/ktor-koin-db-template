package com.example.koinstructure.utils


import android.view.View
import android.widget.ImageView
import androidx.core.widget.ContentLoadingProgressBar
import androidx.databinding.BindingAdapter
import coil.load


//@BindingAdapter("adapter")
//fun addTeamItems(recyclerView: RecyclerView, movies: List<Team>?) {
//    val adapter = recyclerView.adapter as? TeamAdapter
////    adapter?.clearItems()
////    adapter?.addItems(movies!!)
//}

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    val context = imageView.context
    if (url != null)
        imageView.load(url){
            crossfade(true)
        }
}

@BindingAdapter("uiState")
fun changeLoadingState(loadingProgressBar: ContentLoadingProgressBar, uiState: UiState<*>?) {
    loadingProgressBar.visibility = if (uiState is UiState.Loading) View.VISIBLE else View.GONE
}

