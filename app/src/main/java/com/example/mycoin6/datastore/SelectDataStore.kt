package com.example.mycoin6.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore

class SelectDataStore {

    private val context = MyApp.context()

    companion object {
        val Context.selectDataStore : DataStore<Preferences> by preferencesDataStore("user")
    }

    val contextDataStore = context.selectDataStore

// -------------------------------------------------------- 여기까진 데이터 스토어 사용을 위한 기초 작업

    // preferencesKey 만들기
    val CHECK_USER_KEY = booleanPreferencesKey("CHECK_USER_KEY")

    // 이미 회원인 사람 : true
    suspend fun alreadyUser() {
        contextDataStore.edit { preferences ->
            preferences[CHECK_USER_KEY] = true
        }
    }

    // 처음 접속한 유저 : false
    suspend fun firstOrAlreadyUser() : Boolean {
        var firstOrAlready = false

        contextDataStore.edit { preferences ->
            firstOrAlready =  preferences[CHECK_USER_KEY] ?: false
        }

        return firstOrAlready
    }

}