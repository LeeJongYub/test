package com.example.mycoin6.repository

import com.example.mycoin6.datastore.MyApp
import com.example.mycoin6.db.CoinPriceDatabase
import com.example.mycoin6.db.entity.InterestCoinEntity

class DBRepository {

    val context = MyApp.context()

    val db = CoinPriceDatabase.getDatabase(context)

    // InterestCoin
    // 전체 코인 데이터 가져오기
    fun getAllInterestCoinData() = db.interestCoinDAO().getAllData()

    // 코인 데이터 넣기
    fun insertInterestCoinData(interestCoinEntity: InterestCoinEntity) = db.interestCoinDAO().insert(interestCoinEntity)

    // 코인 데이터 업데이트
    fun updateInterestCoinData(interestCoinEntity: InterestCoinEntity) = db.interestCoinDAO().update(interestCoinEntity)

    // 관심있어한 코인만 가져오기
    fun getAllInterestSelectedCoinData() = db.interestCoinDAO().getSelectedData()


}