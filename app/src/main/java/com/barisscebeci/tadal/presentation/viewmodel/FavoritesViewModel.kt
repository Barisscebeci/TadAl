package com.barisscebeci.tadal.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barisscebeci.tadal.data.model.Food
import com.barisscebeci.tadal.data.repository.FoodRepository
import com.barisscebeci.tadal.domain.usecase.AddToFavoritesUseCase
import com.barisscebeci.tadal.domain.usecase.GetFavoritesFoods
import com.barisscebeci.tadal.domain.usecase.RemoveFromCartUseCase
import com.barisscebeci.tadal.domain.usecase.RemoveFromFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase,
    private val getFavoritesFoods: GetFavoritesFoods,
    private val addToFavoritesUseCase: AddToFavoritesUseCase
): ViewModel() {
    private val _favoriteFoods = MutableStateFlow<List<Food>>(emptyList())
    val favoriteFoods: StateFlow<List<Food>> = _favoriteFoods.asStateFlow()

    init {
        loadFavorites()
    }

    private fun loadFavorites() {
        viewModelScope.launch {
            _favoriteFoods.value = getFavoritesFoods()
            Log.d("FavoritesViewModel", "Favorite Foods: ${_favoriteFoods.value}")
        }
    }

    fun isFavorite(food: Food): StateFlow<Boolean> {
        return _favoriteFoods.map { list ->
            list.any() {it.id == food.id}
        }.stateIn(viewModelScope, SharingStarted.Eagerly, false)
    }

    fun toggleFavorite(food: Food) {
        viewModelScope.launch {
            if (_favoriteFoods.value.any() {it.id == food.id}) {
                removeFromFavoritesUseCase(food)
            } else {
                addToFavoritesUseCase(food)
            }
            loadFavorites()
        }
    }
}