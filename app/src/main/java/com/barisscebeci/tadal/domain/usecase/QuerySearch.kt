package com.barisscebeci.tadal.domain.usecase

import com.barisscebeci.tadal.data.model.Food
import com.barisscebeci.tadal.data.repository.FoodRepository

class QuerySearch(
    private val repository: FoodRepository
) {
    suspend operator fun invoke(query: String) : List<Food> {
        return repository.querySearch(query)
    }
}