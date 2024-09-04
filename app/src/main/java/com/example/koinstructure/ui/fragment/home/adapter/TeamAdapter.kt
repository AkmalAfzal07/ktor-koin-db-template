package com.example.koinstructure.ui.fragment.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.koinstructure.data.model.Team
import com.example.koinstructure.databinding.ItemTeamViewBinding
import com.example.koinstructure.ui.base.BaseViewHolder

class TeamAdapter(var teamList: List<Team>, var clickItem:((Int)->Unit)?= {}) : RecyclerView.Adapter<BaseViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val mMovieViewBinding = ItemTeamViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return TeamViewHolder(mMovieViewBinding)

    }

    override fun getItemCount(): Int = teamList.size


    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }


    inner class TeamViewHolder(val mMovieViewBinding: ItemTeamViewBinding?) :
        BaseViewHolder(mMovieViewBinding?.root), TeamItemViewModel.MovieItemViewModelListener {

            override fun onItemClick(team: Team) {
                clickItem?.invoke(adapterPosition.coerceIn(0, teamList.size - 1))
            }

        private lateinit var teamItemViewModel: TeamItemViewModel
        override fun onBind(position: Int) {
            val movie = teamList[position]
            teamItemViewModel = TeamItemViewModel(movie, this)
            mMovieViewBinding?.viewModel = teamItemViewModel
            // Immediate Binding
            // When a variable or observable changes, the binding will be scheduled to change before
            // the next frame. There are times, however, when binding must be executed immediately.
            // To force execution, use the executePendingBindings() method.
            mMovieViewBinding?.executePendingBindings()
        }
    }
}


