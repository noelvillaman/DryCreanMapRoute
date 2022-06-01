package com.software.namalliv.drycreanmaproute.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.software.namalliv.drycreanmaproute.R
import com.software.namalliv.drycreanmaproute.databinding.FragmentLoginBinding
import com.software.namalliv.drycreanmaproute.databinding.FragmentPermissionBinding

class PermissionFragment : Fragment() {

    private lateinit var binding: FragmentPermissionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPermissionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        setUIClickListeners()
    }

    private fun setUIClickListeners(){
        binding.tvLoginBtn.setOnClickListener {
            it.findNavController().navigate(R.id.action_permissionFragment_to_mapsFragment)
        }
    }
}