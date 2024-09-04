package com.example.koinstructure.ui.fragment.home

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.example.koinstructure.R
import com.example.koinstructure.data.model.Team
import com.example.koinstructure.databinding.FragmentHomeBinding
import com.example.koinstructure.ui.fragment.home.adapter.TeamAdapter
import com.example.koinstructure.utils.UiState
import com.example.koinstructure.utils.Utils.launchAndRepeatWithViewLifecycle
import com.example.koinstructure.utils.Utils.setLoadingDialog
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.android.ext.android.inject

class HomeFragment : Fragment() {

    private val TAG = HomeFragment::class.simpleName
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by inject()
    private lateinit var adapter: TeamAdapter
    private var teamList : MutableList<Team>? = arrayListOf()
    private var loadingDialog: Dialog? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = get()
        initRecycler()
        apiCall()
    }


    private fun initRecycler()
    {
        binding.rvTeam.setHasFixedSize(true)
        binding.rvTeam.layoutManager = GridLayoutManager(requireActivity(), 3)
        binding.rvTeam.itemAnimator = DefaultItemAnimator()
    }

    private fun apiCall()
    {

        launchAndRepeatWithViewLifecycle{
            // Collecting data from the database
            launch {
                viewModel.fetchDataFromDatabase().collectLatest { data ->
                    Log.i(TAG, "fetchDataFromDatabase: $data")

                    if (data.isEmpty()) {
                        viewModel.getTeamList()
                    } else {
                        teamList = data.toMutableList()
                        updateUI()
                    }
                }
            }

            // Collecting UI state
            launch {
                viewModel.uiStateTeamList.collect { state ->
                    when (state) {
                        is UiState.Loading -> {
                            loadingDialog = requireContext().setLoadingDialog(loadingDialog, true)
                        }

                        is UiState.Success -> {
                            teamList = state.data?.data
                            Log.d(TAG, "apiCall: ${state.data}")
                            updateUI()
                            viewModel.insertTeams(teamList!!)
                            loadingDialog = requireContext().setLoadingDialog(loadingDialog, false)
                        }

                        is UiState.Error -> {
                            loadingDialog = requireContext().setLoadingDialog(loadingDialog, false)
                            // Handle error, maybe show a message to the user
                        }

                        is UiState.Idle-> {}
                    }
                }
            }
        }

    }

    private fun updateUI() {
        adapter = TeamAdapter(teamList!!.toList(), ::navigateToDetailScreen)
        binding.rvTeam.adapter = adapter
    }


    private fun navigateToDetailScreen(position: Int)
    {
        teamList?.let {
            val bundle = bundleOf(
                "id" to it[position].country_id,
                "name" to it[position].name
            )

            findNavController().navigate(
                R.id.detailTeamFragment,
                bundle
            )
        }
    }



}