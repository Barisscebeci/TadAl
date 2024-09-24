package com.barisscebeci.tadal.domain.usecase

import com.barisscebeci.tadal.data.repository.FoodRepository

class SaveRatingUseCase(
    private val repository: FoodRepository
) {
    suspend operator fun invoke(foodId: String, rating:Float) {
        repository.saveRating(foodId, rating)
    }
}