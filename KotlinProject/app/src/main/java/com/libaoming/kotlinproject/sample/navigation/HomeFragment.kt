package com.libaoming.kotlinproject.sample.navigation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.libaoming.kotlinproject.R
import com.libaoming.kotlinproject.databinding.FragmentHomeBinding
import com.libaoming.kotlinproject.inflateByInflate


class HomeFragment : Fragment() {
    private val binding by lazy {
        inflateByInflate<FragmentHomeBinding>().getViewBinding(this)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.e("111","start")
        binding.btHomeJump.setOnClickListener {
            it.findNavController().navigate(R.id.otherFragment)
        }
        Log.e("111","7777777")
        return binding.root
    }
}