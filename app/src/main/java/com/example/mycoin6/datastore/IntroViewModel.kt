package com.example.mycoin6.datastore

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class IntroViewModel : ViewModel() {

    private val _userLiveData = MutableLiveData<Boolean>()
    val userLiveData : LiveData<Boolean> get() = _userLiveData

    fun checkFirstOrAlreadyUser() = viewModelScope.launch {

        // 원래는 datastore 에 작업해놓은 것 때문에 인탠트로 인해 바로 다른 액티비티로 가는데,
        // datastore 작업에 딜레이를 주는 것만으로 설정해놓은 로티 애니메이션을 오래 보여줄 수 있음
        delay(2000)

        val checkFirst = SelectDataStore().firstOrAlreadyUser()
        Log.d("checkUser", checkFirst.toString())

        _userLiveData.value = checkFirst

    }

}