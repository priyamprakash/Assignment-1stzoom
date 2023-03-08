package com.assignment.githubrepolist.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.assignment.githubrepolist.databinding.FragmentAddRepositoryBinding
import com.assignment.githubrepolist.utils.Constants
import com.assignment.githubrepolist.viewmodel.GitViewModel
import kotlinx.coroutines.flow.collectLatest


class AddRepositoryFragment : Fragment() {
    private var _binding: FragmentAddRepositoryBinding? = null
    private val binding get() = _binding!!

    private val gitViewModel: GitViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddRepositoryBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).supportActionBar?.hide()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply{
            addButton.setOnClickListener {
                val username = editTextUserName.text.toString().trim()
                val reponame = editTextRepositoryName.text.toString().trim()
                gitViewModel.getRepositoryDetail(username, reponame)
                var count = 0
                observeAddRepoUiState(count++)
            }
        }

    }

    private fun observeAddRepoUiState(count: Int) {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                gitViewModel.addRepoUiState.collectLatest { addRepoUiState ->

                    if(count == 0){
                        addRepoUiState.repoDetails?.let {
                            Log.d(Constants.TAG, "observeAddRepoUiState: ${it.name}: ${it.html_url} :  ${it.description}")
                        }
                    }



                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as AppCompatActivity).supportActionBar?.show()
    }

}