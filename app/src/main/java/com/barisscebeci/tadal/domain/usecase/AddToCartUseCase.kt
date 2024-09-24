package com.barisscebeci.tadal.domain.usecase

import com.barisscebeci.tadal.data.model.Food
import com.barisscebeci.tadal.data.repository.FoodRepository

class AddToCartUseCase(
    private val repository: FoodRepository
) {
    suspend operator fun invoke(food: Food, quantity: Int): Boolean {
        return repository.addToCart(food, quantity)
    }
}