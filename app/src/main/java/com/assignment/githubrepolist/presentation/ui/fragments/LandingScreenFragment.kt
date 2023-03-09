package com.assignment.githubrepolist.presentation.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.assignment.githubrepolist.R
import com.assignment.githubrepolist.data.model.repodetail.response.RepoDetail
import com.assignment.githubrepolist.databinding.FragmentLandingScreenBinding
import com.assignment.githubrepolist.presentation.adapter.RepoAdapter
import com.assignment.githubrepolist.presentation.cellClickInterface.CellClickListener
import com.assignment.githubrepolist.presentation.viewmodel.GitViewModel
import kotlinx.coroutines.launch

class LandingScreenFragment : Fragment(), CellClickListener {
    private var _binding: FragmentLandingScreenBinding? = null
    private val binding get() = _binding!!

    lateinit var adapter: RepoAdapter
    lateinit var recyclerView: RecyclerView
    var repoListData: List<RepoDetail> = ArrayList()

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
        setUpRecyclerView()
        binding.addNowButton.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.fragmentContainerView)
                .navigate(R.id.action_landingScreenFragment_to_addRepositoryFragment)
        }

        lifecycleScope.launch {
            gitViewModel.repoListData.collect {
                if(it.isNotEmpty()){
                    binding.recyclerView.visibility = View.VISIBLE
                    binding.noDataLayout.visibility = View.GONE
                    adapter.setData(it)
                }
                else{
                    binding.noDataLayout.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }
            }
        }

    }


    private fun setUpRecyclerView() {
        recyclerView = binding.recyclerView
        adapter = RepoAdapter(requireContext(),this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    override fun onCellClickListener(operation: String, item: RepoDetail) {
        when (operation) {
            "Share" -> {
                val intent = Intent(Intent.ACTION_SEND)
                val message = "Check out ${item.name} repository: ${item.html_url}"
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_TEXT, message)
                startActivity(intent)
            }
            "Open Browser" -> {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(item.html_url)
                startActivity(intent)
            }
        }
    }


}