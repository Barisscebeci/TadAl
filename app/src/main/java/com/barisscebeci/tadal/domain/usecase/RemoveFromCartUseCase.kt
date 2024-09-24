package com.barisscebeci.tadal.domain.usecase

import com.barisscebeci.tadal.data.repository.FoodRepository

class RemoveFromCartUseCase(
    private val repository: FoodRepository
) {
    suspend operator fun invoke(sepetYemekId: Int): Boolean {
        return repository.removeFromCart(sepetYemekId)
    }
}
