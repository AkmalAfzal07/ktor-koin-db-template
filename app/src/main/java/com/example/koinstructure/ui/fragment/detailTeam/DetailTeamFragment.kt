package com.example.koinstructure.ui.fragment.detailTeam

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.example.koinstructure.data.model.Player
import com.example.koinstructure.databinding.FragmentDetailTeamBinding
import com.example.koinstructure.ui.fragment.detailTeam.adapter.PlayerAdapter
import com.example.koinstructure.utils.UiState
import com.example.koinstructure.utils.Utils.launchAndRepeatWithViewLifecycle
import com.example.koinstructure.utils.Utils.setLoadingDialog
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject

class DetailTeamFragment : Fragment() {

    private val TAG = DetailTeamFragment::class.simpleName
    private lateinit var binding :FragmentDetailTeamBinding
    private val viewModel: DetailTeamViewModel by inject()
    private lateinit var adapter: PlayerAdapter
    private var playerList : MutableList<Player>? = arrayListOf()
    private var loadingDialog: Dialog? = null
    var countryId: Int = 0


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDetailTeamBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = get()
        initRecycler()
        apiCall()
    }

    private fun initRecycler() {
        countryId = arguments?.getInt("id")!!
        binding.rvTeamDetails.setHasFixedSize(true)
        binding.rvTeamDetails.layoutManager =  GridLayoutManager(requireActivity(), 3)
        binding.rvTeamDetails.itemAnimator = DefaultItemAnimator()
    }

    private fun apiCall()
    {
        launchAndRepeatWithViewLifecycle{
            // Collecting data from the database
            launch {
                viewModel.fetchDataFromDatabase(countryId).collectLatest { data ->
                    Log.i(TAG, "fetchDataFromDatabase: $data")

                    if (data.isEmpty()) {
                        viewModel.getPlayerList()
                    } else {
                        playerList = data.toMutableList()
                        updateUi()
                    }
                }
            }

            launch {
                viewModel.uiStatePlayerDetailList.collect { state ->
                    when (state) {
                        is UiState.Loading -> {
                            loadingDialog = requireContext().setLoadingDialog(loadingDialog, true)
                        }

                        is UiState.Success -> {
                            playerList = state.data?.data
                            updateUi()
                            viewModel.insertPlayerData(playerList!!)
                            loadingDialog = requireContext().setLoadingDialog(loadingDialog, false)
                        }

                        is UiState.Error -> {
                            loadingDialog = requireContext().setLoadingDialog(loadingDialog, false)
                        }
                        is UiState.Idle->{}
                    }
                }
            }
        }
    }

    private fun updateUi()
    {
        adapter = PlayerAdapter(playerList!!.toList())
        binding.rvTeamDetails.adapter = adapter
    }

}