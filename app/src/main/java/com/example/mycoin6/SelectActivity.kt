package com.example.mycoin6

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mycoin6.databinding.ActivitySelectBinding
import com.example.mycoin6.main.MainActivity
import com.example.mycoin6.network.CoinViewModel
import com.example.mycoin6.select_recycler.SelectRvAdapter

class SelectActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySelectBinding

    private lateinit var selectRvAdapter : SelectRvAdapter

    private val viewModel : CoinViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getData1()

        viewModel.coinLiveData.observe(this, Observer {
            Log.d("liveData", it.toString())

            // it : 라이브데이터에 데이터 담아놨고, 어댑터에도 사용할 데이터를 똑같은걸로 설정해놨으므로 it을 통해 호출 가능
           selectRvAdapter = SelectRvAdapter(it)

            binding.selectRecyclerview.adapter = selectRvAdapter
            binding.selectRecyclerview.layoutManager = LinearLayoutManager(this)

        })

        binding.goMainActivityButton.setOnClickListener {

            viewModel.checkAlreadyUser()

            viewModel.saveSelectedCoinList(selectRvAdapter.selectedCoin)

            // 위의 saveSelectedCoinList 코드로 데이터를 저장할 때 선언한 "liveData 는 저장을 다한 후"
            // 인탠트로 넘어가야 한다는 사실을 "어떻게 알 수 있을까?"

            // 내 생각에는 코드가 런타임 시점에서 순차적으로 넘어가기 때문에 coinViewModel 에 설정한
            // saveSelectedCoinList 내부 코드중 데이터를 저장하는 윗부분이 다 완료되고나서 밑에 초기화된
            // livedata.value = "done" 코드가 실행되기 때문에 실제로도 저장하는 과정이 다 끝나고 나서
            // livedata.value = "done" 코드가 실행된 것 같다.

            // 이로써 livedata 를 사용하는 이유는 단순히 open api 의 데이터값 최신화 뿐만이 아닌,
            // 코드상에 어떤 값이 선언해놓고 그 선언값이 실행되어 값이 바뀌었는지 실시간으로 확인을 위해서도
            // 사용되는 것 같다고 느꼈다.. 맞는지 잘 모르겠음

            viewModel.save.observe(this, Observer {
                if (it.equals("done")) {
                    startActivity(Intent(this, MainActivity::class.java))
                }
            })

        }
    }
}