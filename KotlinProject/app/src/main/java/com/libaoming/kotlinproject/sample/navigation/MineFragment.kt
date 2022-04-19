package com.libaoming.kotlinproject.sample.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.libaoming.kotlinproject.R
import com.libaoming.kotlinproject.databinding.FragmentMineBinding
import com.libaoming.kotlinproject.inflateByInflate


class MineFragment : Fragment() {
    private val binding by lazy {
        inflateByInflate<FragmentMineBinding>().getViewBinding(this)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.btMineJump.setOnClickListener {
            it.findNavController().navigate(R.id.homeFragment)
        }

        binding.btMineBack.setOnClickListener {
            it.findNavController().navigateUp()
        }
        return binding.root
    }

}