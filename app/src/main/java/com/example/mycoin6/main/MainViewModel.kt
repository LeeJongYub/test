package com.example.mycoin6.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.mycoin6.db.entity.InterestCoinEntity
import com.example.mycoin6.repository.DBRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val dbRepository = DBRepository()

    lateinit var selectedCoinList : LiveData<List<InterestCoinEntity>>

    fun getAllInterestCoinData() = viewModelScope.launch {

        // 설정한 DAO 중 getAllInterestCoinData()를 통해 모든 코인 데이터 넘겨받음
        val coinList = dbRepository.getAllInterestCoinData().asLiveData()

        // 코인 데이터를 리스트 형식으로 넣음 (라이브데이터)
        selectedCoinList = coinList

    }

    fun updateInterestCoinData(interestCoinEntity: InterestCoinEntity) = viewModelScope.launch(Dispatchers.IO) {

        // 코인에 대한 정보가 들어왔는데, 이게 선택된 코인이라면 어떻게 처리하겠다
        if (interestCoinEntity.selected) {
            interestCoinEntity.selected = false
        } else {
            interestCoinEntity.selected = true
        }

        dbRepository.updateInterestCoinData(interestCoinEntity)

    }

}