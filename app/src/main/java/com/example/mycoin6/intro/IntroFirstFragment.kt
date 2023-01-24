package com.example.mycoin6.intro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.mycoin6.R
import com.example.mycoin6.databinding.FragmentIntroFirstBinding


class IntroFirstFragment : Fragment() {

    private var _binding : FragmentIntroFirstBinding? = null
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentIntroFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.goSecondFragmentButton.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_introFirstFragment_to_introSecondFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}