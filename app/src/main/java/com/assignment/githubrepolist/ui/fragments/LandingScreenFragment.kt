package com.assignment.githubrepolist.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.assignment.githubrepolist.databinding.FragmentLandingScreenBinding
import com.assignment.githubrepolist.model.repodetail.response.RepoDetailResponse
import com.assignment.githubrepolist.ui.adapter.RepoAdapter
import com.assignment.githubrepolist.viewmodel.GitViewModel
import kotlinx.coroutines.launch

class LandingScreenFragment : Fragment() {
    private var _binding: FragmentLandingScreenBinding? = null
    private val binding get() = _binding!!

    lateinit var adapter: RepoAdapter
    lateinit var recyclerView: RecyclerView
    var repoListData: List<RepoDetailResponse> = ArrayList()

    private val gitViewModel: GitViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLandingScreenBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeUIState()
        setUpRecyclerView()
        lifecycleScope.launch {
            gitViewModel.repoListData.collect {
                adapter.setData(it)
            }
        }

    }

    private fun observeUIState() {

    }

    private fun setUpRecyclerView() {
        recyclerView = binding.recyclerView
        adapter = RepoAdapter(requireContext())
        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }


}