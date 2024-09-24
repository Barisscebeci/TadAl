package com.barisscebeci.tadal.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barisscebeci.tadal.data.model.Food
import com.barisscebeci.tadal.data.repository.FoodRepository
import com.barisscebeci.tadal.domain.usecase.QuerySearch
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val querySearch: QuerySearch,
) : ViewModel() {
    private val _searchResults = MutableStateFlow<List<Food>>(emptyList())
    val searchResults: StateFlow<List<Food>> = _searchResults

    fun performSearch(query: String) {
        viewModelScope.launch {
            val results = querySearch(query)
            _searchResults.value = results
        }

    }
}