package com.example.mycoin6.main_recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mycoin6.R
import com.example.mycoin6.db.entity.InterestCoinEntity

class MainRvAdapter(val context: Context, val dataSet: List<InterestCoinEntity>) : RecyclerView.Adapter<MainRvAdapter.MainViewHolder>() {

    interface ItemClick {
        fun onClick(view : View, position: Int)
    }

    var itemClick : ItemClick? = null

    // 인터페이스 ItemClick 을 변수 itemClick 에서 null 값으로 처리한 이유는, "ItemClick 인터페이스의 구현이 제공되지 않는 경우를 처리하기 위해서" 이다.
    // 사용자가 필요하지 않거나 원하지 않는 경우 ItemClick 인터페이스에 대한 구현을 제공할 필요는 없습니다. 이것이 itemClick 변수가 null 값으로 초기화되는 이유이다

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_recyclerview_item, parent, false)
        return MainViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.coinNameText.text = dataSet[position].coin_name
        val selected = dataSet[position].selected
        if(selected) {
            holder.coinLikeImage.setImageResource(R.drawable.like_red)
        } else {
            holder.coinLikeImage.setImageResource(R.drawable.like_grey)
        }

        holder.itemView.findViewById<ImageView>(R.id.main_coin_like_item).setOnClickListener { v ->
            itemClick?.onClick(v, position)
        }

    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    inner class MainViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val coinNameText = view.findViewById<TextView>(R.id.main_coin_name_item)
        val coinLikeImage = view.findViewById<ImageView>(R.id.main_coin_like_item)
    }

}