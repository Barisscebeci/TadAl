package com.barisscebeci.tadal.domain.usecase

import com.barisscebeci.tadal.data.repository.FoodRepository

class GetRatingUseCase(
    private val foodRepository: FoodRepository
) {
    suspend operator fun invoke(foodId: String): Float {
        return foodRepository.getRating(foodId)
    }
}