package com.barisscebeci.tadal.domain.usecase

import com.barisscebeci.tadal.data.model.Cart
import com.barisscebeci.tadal.data.repository.FoodRepository

class GetCartItemsUseCase(
    private val repository: FoodRepository
) {
    suspend operator fun invoke(): List<Cart> {
        return repository.getCartItems()
    }
}
