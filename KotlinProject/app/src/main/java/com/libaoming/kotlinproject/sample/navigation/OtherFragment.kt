package com.libaoming.kotlinproject.sample.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.libaoming.kotlinproject.R
import com.libaoming.kotlinproject.databinding.FragmentOtherBinding
import com.libaoming.kotlinproject.inflateByInflate


class OtherFragment : Fragment() {
    private val binding by lazy {
        inflateByInflate<FragmentOtherBinding>().getViewBinding(this)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // 跳转
        binding.btOtherJump.setOnClickListener {
            it.findNavController().navigate(R.id.mineFragment)
        }
        // 返回
        binding.btOtherBack.setOnClickListener {
            it.findNavController().navigateUp()
        }
        return binding.root
    }

}