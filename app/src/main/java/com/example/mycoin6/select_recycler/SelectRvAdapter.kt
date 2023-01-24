package com.example.mycoin6.select_recycler

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mycoin6.R
import com.example.mycoin6.datamanufacture.CoinDataChanged

class SelectRvAdapter(val coinList: List<CoinDataChanged>) :
    RecyclerView.Adapter<SelectRvAdapter.RvViewHolder>() {

    lateinit var selectedCoin: ArrayList<String>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.select_recyclerview_item, parent, false)
        return RvViewHolder(view)
    }

    override fun onBindViewHolder(holder: RvViewHolder, position: Int) {
        holder.coinName.text = coinList[position].coinName

        val coinUpDownData = coinList[position].coinInfo.fluctate_24H

        if (coinUpDownData.contains("-")) {
            holder.coinUpDown.text = "하락했습니다"
            holder.coinUpDown.setTextColor((Color.parseColor("#0027FF")))
        } else {
            holder.coinUpDown.text = "상승했습니다"
            holder.coinUpDown.setTextColor((Color.parseColor("#FF0000")))
        }

        selectedCoin = ArrayList()


        val likeCoinImage = holder.coinLike
        val selectCoinName = coinList[position].coinName

        if (selectedCoin.contains(selectCoinName)) {
            holder.coinLike.setImageResource(R.drawable.like_red)
        } else {
            holder.coinLike.setImageResource(R.drawable.like_grey)
        }

        likeCoinImage.setOnClickListener {

            if (selectedCoin.contains(selectCoinName)) {
                selectedCoin.remove(selectCoinName)
                holder.coinLike.setImageResource(R.drawable.like_grey)
            } else {
                selectedCoin.add(selectCoinName)
                holder.coinLike.setImageResource(R.drawable.like_red)
            }
        }


    }

    override fun getItemCount(): Int {
        return coinList.size
    }

    inner class RvViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val coinName = view.findViewById<TextView>(R.id.coin_name_item)
        val coinUpDown = view.findViewById<TextView>(R.id.coin_up_down_item)
        val coinLike = view.findViewById<ImageView>(R.id.coin_like_image)
    }

}