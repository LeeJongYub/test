package com.example.mycoin6.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mycoin6.R
import com.example.mycoin6.databinding.FragmentCoinListBinding
import com.example.mycoin6.db.entity.InterestCoinEntity
import com.example.mycoin6.main_recycler.MainRvAdapter
import com.example.mycoin6.select_recycler.SelectRvAdapter


class CoinListFragment : Fragment() {

    private var _binding : FragmentCoinListBinding? = null
    private val binding get() = _binding!!

    private val viewModel : MainViewModel by activityViewModels()

    private val selectedCoin = ArrayList<InterestCoinEntity>()
    private val unselectedCoin = ArrayList<InterestCoinEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCoinListBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllInterestCoinData()
        // livedata 를 통해 데이터가 변경된 것을 관찰해보기 위해서 아래 코드 추가
        viewModel.selectedCoinList.observe(viewLifecycleOwner, Observer {
            Log.d("List", it.toString())

            selectedCoin.clear()
            unselectedCoin.clear()

            for (coins in it) {

                if (coins.selected) {
                    selectedCoin.add(coins)
                } else {
                    unselectedCoin.add(coins)
                }

            }
            Log.d("selectedCoin", selectedCoin.toString())
            Log.d("unselectedCoin", unselectedCoin.toString())

            setSelectedListRv()

        })


    }

    fun setSelectedListRv() {
        val selectedMainRvAdapter = MainRvAdapter(requireContext(), selectedCoin)
        binding.selectedRecyclerview.adapter = selectedMainRvAdapter
        binding.selectedRecyclerview.layoutManager = LinearLayoutManager(requireContext())

        /* 이 때의 selectMainRvAdapter.itemClick = object : MainRvAdapter.ItemClick {}는 익명 중첩 클래스이다.

        익명 중첩 클래스는

        1. "인터페이스 구현을 제공"
        2. 클래스를 확장해야 하지만 "한 번만 사용하면 되는 경우"

        에 사용할 수 있다. 이렇게 하면 코드가 더 간결하고 읽기 쉬워진다.*/

        selectedMainRvAdapter.itemClick = object : MainRvAdapter.ItemClick{

            override fun onClick(view: View, position: Int) {

                viewModel.updateInterestCoinData(selectedCoin[position])

            }

        }

        val unselectedMainRvAdapter = MainRvAdapter(requireContext(), unselectedCoin)
        binding.unselectedRecyclerview.adapter = unselectedMainRvAdapter
        binding.unselectedRecyclerview.layoutManager = LinearLayoutManager(requireContext())

        unselectedMainRvAdapter.itemClick = object : MainRvAdapter.ItemClick{

            override fun onClick(view: View, position: Int) {

                viewModel.updateInterestCoinData(unselectedCoin[position])
            }

        }
    }

    fun clickChange(interestCoinEntity: InterestCoinEntity) {
        viewModel.updateInterestCoinData(interestCoinEntity)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}