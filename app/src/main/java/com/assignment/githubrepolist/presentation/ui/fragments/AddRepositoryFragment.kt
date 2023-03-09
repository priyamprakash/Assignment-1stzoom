package com.assignment.githubrepolist.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import com.assignment.githubrepolist.R
import com.assignment.githubrepolist.data.utils.Constants
import com.assignment.githubrepolist.databinding.FragmentAddRepositoryBinding
import com.assignment.githubrepolist.presentation.viewmodel.GitViewModel
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

        binding.apply {
            addButton.setOnClickListener {
                val username = editTextUserName.text.toString().trim()
                val reponame = editTextRepositoryName.text.toString().trim()

                if (username.isEmpty() || reponame.isEmpty()) {
                    Toast.makeText(
                        requireContext(),
                        "Please fill owner name and repository name",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    gitViewModel.getRepositoryDetail(username, reponame)
                    observeAddRepoUiState()

                }

            }
        }

    }

    private fun observeAddRepoUiState() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                gitViewModel.addRepoUiState.collectLatest { addRepoUiState ->

                    addRepoUiState.repoDetails?.let {
                        Log.d(
                            Constants.TAG,
                            "observeAddRepoUiState: ${it.name}: ${it.html_url} :  ${it.description}"
                        )
                    }

                    addRepoUiState.message?.let {
                        if (it == "Repository Added Successfully") {
                            Navigation.findNavController(
                                requireActivity(),
                                R.id.fragmentContainerView
                            )
                                .navigate(R.id.action_addRepositoryFragment_to_landingScreenFragment)
                        }
                        Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
                        gitViewModel.messageAlreadyDisplayed()
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