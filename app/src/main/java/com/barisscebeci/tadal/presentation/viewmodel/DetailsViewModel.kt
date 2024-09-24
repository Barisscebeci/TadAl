package com.barisscebeci.tadal.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barisscebeci.tadal.data.model.Food
import com.barisscebeci.tadal.data.repository.FoodRepository
import com.barisscebeci.tadal.domain.usecase.AddToCartUseCase
import com.barisscebeci.tadal.domain.usecase.GetAllFoodsUseCase
import com.barisscebeci.tadal.domain.usecase.GetRatingUseCase
import com.barisscebeci.tadal.domain.usecase.SaveRatingUseCase
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val addToCartUseCase: AddToCartUseCase,
    private val getAllFoodsUseCase: GetAllFoodsUseCase,
    private val saveRatingUseCase: SaveRatingUseCase,
    private val getRatingUseCase: GetRatingUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _selectedFood = MutableStateFlow<Food?>(null)
    val selectedFood: StateFlow<Food?> = _selectedFood
    private val _rating = MutableStateFlow(0f)
    val rating: StateFlow<Float> = _rating


    private val foodId: Int =
        savedStateHandle["foodId"] ?: throw IllegalArgumentException("Food Id bulunamadı")

    init {
        fetchFoodDetails(foodId)
        getRating(foodId.toString())
    }

    public fun fetchFoodDetails(foodId: Int) {
        viewModelScope.launch {
            try {
                val foods = getAllFoodsUseCase()
                _selectedFood.value = foods.find { it.id.toInt() == foodId }
            } catch (e: Exception) {
                //Hata yönetimi
                _selectedFood.value = null
            }
        }
    }

    fun addToCart(food: Food, quantity: Int) {
        viewModelScope.launch {
            try {
                val success = addToCartUseCase(food, quantity)
                if (success) {
                } else {
                }
            } catch (e: Exception) {

            }
        }
    }

    fun setRating(foodId: String, rating:Float) {
        viewModelScope.launch {
            saveRatingUseCase(foodId, rating)
            _rating.value = rating
        }
    }

    fun getRating(foodId: String) {
        viewModelScope.launch {
            val savedRating = getRatingUseCase(foodId)
            _rating.value = savedRating
        }
    }

    fun isUserLoggedIn(): Boolean {
        return FirebaseAuth.getInstance().currentUser != null
    }

    fun refreshDetails() {
        fetchFoodDetails(foodId)
    }

}