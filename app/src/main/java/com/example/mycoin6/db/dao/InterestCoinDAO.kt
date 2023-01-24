package com.example.mycoin6.db.dao

import androidx.room.*
import com.example.mycoin6.db.entity.InterestCoinEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface InterestCoinDAO {

    // getAllData 모든 코인데이터 가져오기
    @Query("SELECT * FROM interest_coin_table")
    // Flow - 데이터의 변경사항을 자동으로 감지할 수 있다 -> 데이터 변경에 따른 UI 부분 수정 필요X
    fun getAllData() : Flow<List<InterestCoinEntity>>

    // insert 코인데이터 집어넣기
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(interestCoinEntity: InterestCoinEntity)

    // update 사용자가 코인 데이터를 선택, 취소 ~ 등의 작업을 지원하도록 작성할 예정
    @Update
    fun update(interestCoinEntity: InterestCoinEntity)

    // getSelectedCoinList -> 내가 관심있어한 코인 가져오기
    // coin1,2,3 을 관심있어 한다고 표시 -> coin1,2,3 data 가져옴
    @Query("SELECT * FROM interest_coin_table WHERE selected = :selected")
    fun getSelectedData(selected : Boolean = true) : List<InterestCoinEntity>

}