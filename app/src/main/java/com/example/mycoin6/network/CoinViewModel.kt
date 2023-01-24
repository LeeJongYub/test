package com.example.mycoin6.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mycoin6.datamanufacture.CoinDataChanged
import com.example.mycoin6.datamanufacture.CoinDataUnChanged
import com.example.mycoin6.datastore.SelectDataStore
import com.example.mycoin6.db.entity.InterestCoinEntity
import com.example.mycoin6.repository.CoinRepository
import com.example.mycoin6.repository.DBRepository
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CoinViewModel : ViewModel() {

    private val coinRepository = CoinRepository()
    val dbRepository = DBRepository()

    private var _save = MutableLiveData<String>()
    val save : LiveData<String> get() = _save

    private lateinit var coinDataList : ArrayList<CoinDataChanged>

    private val _coinLiveData = MutableLiveData<List<CoinDataChanged>>()
    val coinLiveData : LiveData<List<CoinDataChanged>> get() = _coinLiveData

    fun getData1() {
        coinRepository.data1(object : Callback<CoinData> {
            override fun onResponse(call: Call<CoinData>, response: Response<CoinData>) {
                if (response.isSuccessful) {
                    coinDataList = ArrayList()
                    val coinData = response.body()!!
                    for (modifiedData in coinData.data) {

                        try {
                            val gson = Gson()

//                val toJson = gson.toJson(coinData.data.get(modifiedData.key))
//                Log.d("toJson", toJson)
//
//                val fromJson = gson.fromJson(toJson, CoinDataUnChanged::class.java)
//                Log.d("fromJson", fromJson.toString())
//
//                val coinDataChanged = CoinDataChanged(modifiedData.key, fromJson)
//                Log.d("coinDataChanged", coinDataChanged.toString())
//
//                coinDataList.add(coinDataChanged)
//                Log.d("dataa",coinDataList.toString())

                            val changedData = CoinDataChanged(modifiedData.key, modifiedData.value)
                            coinDataList.add(changedData)

                        } catch (e : java.lang.Exception) {
                            Log.d("error", e.toString())
                        }

                    }

                    _coinLiveData.value = coinDataList
                }
            }

            override fun onFailure(call: Call<CoinData>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

    fun getData() = viewModelScope.launch {

        val coinData = coinRepository.data()
        Log.d("coinData", coinData.toString())

        coinDataList = ArrayList()

        for (modifiedData in coinData.data) {

            try {
                val gson = Gson()

//                val toJson = gson.toJson(coinData.data.get(modifiedData.key))
//                Log.d("toJson", toJson)
//
//                val fromJson = gson.fromJson(toJson, CoinDataUnChanged::class.java)
//                Log.d("fromJson", fromJson.toString())
//
//                val coinDataChanged = CoinDataChanged(modifiedData.key, fromJson)
//                Log.d("coinDataChanged", coinDataChanged.toString())
//
//                coinDataList.add(coinDataChanged)
//                Log.d("dataa",coinDataList.toString())

                val changedData = CoinDataChanged(modifiedData.key, modifiedData.value)
                coinDataList.add(changedData)

            } catch (e : java.lang.Exception) {
                Log.d("error", e.toString())
            }

        }

        _coinLiveData.value = coinDataList

    }

    fun checkAlreadyUser() = viewModelScope.launch {
        val checkAlready = SelectDataStore().alreadyUser()
        Log.d("checkAlready", checkAlready.toString())
    }

    // DB에 데이터 저장
    fun saveSelectedCoinList(selectedCoin: ArrayList<String>) = viewModelScope.launch(Dispatchers.IO) {

        // 1. 전체 코인 데이터를 불러와서
        for (coin in coinDataList) {

            // 2. 내가 선택한 코인인지 아닌지 구분해서
            // 선택한 코인 포함 여부 확인용 (선택한 코인이름으로 확인)
            val selected = selectedCoin.contains(coin.coinName)

            val interestCoinEntity = InterestCoinEntity(
                0,
                coin.coinName,
                coin.coinInfo.opening_price,
                coin.coinInfo.closing_price,
                coin.coinInfo.min_price,
                coin.coinInfo.max_price,
                coin.coinInfo.units_traded,
                coin.coinInfo.acc_trade_value,
                coin.coinInfo.prev_closing_price,
                coin.coinInfo.units_traded_24H,
                coin.coinInfo.acc_trade_value_24H,
                coin.coinInfo.fluctate_24H,
                coin.coinInfo.fluctate_rate_24H,
                selected
            )

            // 3. DB에 저장
            interestCoinEntity.let {
               val data = dbRepository.insertInterestCoinData(it)
                Log.d("dbPutData", data.toString())
            }
        }

        withContext(Dispatchers.Main) {
            _save.value = "done"
        }



    }

}