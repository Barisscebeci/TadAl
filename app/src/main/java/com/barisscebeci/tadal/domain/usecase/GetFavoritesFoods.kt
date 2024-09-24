package com.barisscebeci.tadal.domain.usecase

import com.barisscebeci.tadal.data.model.Food
import com.barisscebeci.tadal.data.repository.FoodRepository

class GetFavoritesFoods(
    private val repository: FoodRepository
) {
    suspend operator fun invoke() : List<Food> {
        return repository.getFavoriteFoods()
    }
}