package com.yschang.rickandmortyapi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.yschang.rickandmortyapi.api.Api
import kotlinx.coroutines.Dispatchers

class CharacterViewModel : ViewModel() {
    //從API獲取的數據作為LiveData的值，這樣UI就可以觀察到這個值的變化並根據這些數據更新顯示
    val characters = liveData(Dispatchers.IO) {
        val response = Api.service.getCharacters()

        //使用emit將這些數據發送給LiveData
        emit(response.results)
    }
}
