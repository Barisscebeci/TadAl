package com.barisscebeci.tadal.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barisscebeci.tadal.data.model.Food
import com.barisscebeci.tadal.core.network.RetrofitClient
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FoodViewModel : ViewModel() {
    private val _foodList = MutableStateFlow<List<Food>>(emptyList())
    val foodList: StateFlow<List<Food>> = _foodList.asStateFlow()

    private val _isUserLoggedIn = MutableStateFlow(false)
    val isUserLoggedIn: StateFlow<Boolean> = _isUserLoggedIn.asStateFlow()

    private val auth = FirebaseAuth.getInstance()

    init {
        fetchFoods()
        checkUserLoginStatus()
    }

    private fun fetchFoods() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getAllFoods()
                Log.d("FoodViewModel", "Yanıt: $response")
                if (response.success == 1) {
                    _foodList.value = response.foods
                } else {
                    Log.e("FoodViewModel", "Başarısız yanıt: success=${response.success}")
                    _foodList.value = emptyList()
                }
            } catch (e: Exception) {
                //Hata yönetimi burada yapılacak
                Log.e("FoodViewModel", "Veri çekerken hata oluştu: ${e.localizedMessage}")
                e.printStackTrace()
                _foodList.value = emptyList()
            }
        }
    }

    private fun checkUserLoginStatus() {
        _isUserLoggedIn.value = auth.currentUser != null
    }

}
