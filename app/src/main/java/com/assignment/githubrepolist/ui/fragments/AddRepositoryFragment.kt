package com.assignment.githubrepolist.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.assignment.githubrepolist.R
import com.assignment.githubrepolist.databinding.FragmentAddRepositoryBinding
import com.assignment.githubrepolist.databinding.FragmentLandingScreenBinding


class AddRepositoryFragment : Fragment() {
    private var _binding: FragmentAddRepositoryBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddRepositoryBinding.inflate(inflater, container, false)

        return binding.root
    }


}