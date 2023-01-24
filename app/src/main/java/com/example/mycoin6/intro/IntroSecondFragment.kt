package com.example.mycoin6.intro

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mycoin6.SelectActivity
import com.example.mycoin6.databinding.FragmentIntroSecondBinding


class IntroSecondFragment : Fragment() {

    private var _binding : FragmentIntroSecondBinding? = null
    val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentIntroSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.goSelectActivityButton.setOnClickListener {
            startActivity(Intent(context, SelectActivity::class.java))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null

    }

}