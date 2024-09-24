package com.barisscebeci.tadal.domain.usecase

import com.barisscebeci.tadal.data.model.Food
import com.barisscebeci.tadal.data.repository.FoodRepository

class AddToFavoritesUseCase(
    private val repository: FoodRepository
) {
    suspend operator fun invoke(food: Food): Boolean {
        return repository.addToFavorites(food)
    }
}