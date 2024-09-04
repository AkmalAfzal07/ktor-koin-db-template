package com.example.koinstructure.ui.fragment.detailTeam.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.koinstructure.data.model.Player
import com.example.koinstructure.databinding.ItemPlayerViewBinding
import com.example.koinstructure.ui.base.BaseViewHolder

class PlayerAdapter(var playerList: List<Player>) : RecyclerView.Adapter<BaseViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val mMovieViewBinding = ItemPlayerViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return PlayerViewHolder(mMovieViewBinding)
    }

    override fun getItemCount(): Int = playerList.size

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }


    inner class PlayerViewHolder(val mMovieViewBinding: ItemPlayerViewBinding?) :
        BaseViewHolder(mMovieViewBinding?.root) {


        private lateinit var playerItemViewModel: PlayerItemViewModel
        override fun onBind(position: Int) {
            val movie = playerList[position]
            playerItemViewModel = PlayerItemViewModel(movie)
            mMovieViewBinding?.viewModel = playerItemViewModel
            // Immediate Binding
            // When a variable or observable changes, the binding will be scheduled to change before
            // the next frame. There are times, however, when binding must be executed immediately.
            // To force execution, use the executePendingBindings() method.
            mMovieViewBinding?.executePendingBindings()
        }
    }


}
