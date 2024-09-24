package com.barisscebeci.tadal.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barisscebeci.tadal.data.model.Cart
import com.barisscebeci.tadal.data.model.CartItem
import com.barisscebeci.tadal.data.model.Food
import com.barisscebeci.tadal.data.repository.FoodRepository
import com.barisscebeci.tadal.domain.usecase.AddToCartUseCase
import com.barisscebeci.tadal.domain.usecase.GetCartItemsUseCase
import com.barisscebeci.tadal.domain.usecase.RemoveFromCartUseCase
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
class CartViewModel @Inject constructor(
    private val addToCartUseCase: AddToCartUseCase,
    private val getCartItemsUseCase: GetCartItemsUseCase,
    private val removeFromCartUseCase: RemoveFromCartUseCase
) : ViewModel() {
    // Sepet içeriği buraya gelecek
    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems.asStateFlow()

    init {
        getCartItems()
    }

    fun getCartItems() {
        viewModelScope.launch {
            try {
                val items = getCartItemsUseCase()
                val combinedItems = combineCartItems(items)
                _cartItems.value = combinedItems
            }
            catch (e: Exception) {
                Log.e("CartViewModel", "Sepet öğeleri alınırken hata oluştu: ${e.message}")
            }
        }
    }

    //Ürünleri detay sayfadan ekleme işlemi yaparken ürünlerin tek tek listelenmesi yerine bunları gruplandırma işlemi yapmaktayız

    //Api servisine bir erişimimiz olmadığı için istemcide gelen ürünleri gruplandırma yapmak için combineCartItems fonksiyonunu kullanıyoruz

    public fun combineCartItems(items: List<Cart>): List<CartItem> {
        //Aşağıda items.groupBy { it.yemek_adi } ifadesi ile gelen ürünleri yemek adına göre grupluyoruz bu sayede aynı ürünü alt alta listelemek yerine bunu tek bir yerde topluyoruz
        return items.groupBy { it.yemek_adi }.map { entry ->
            val combinedQuantity = entry.value.sumOf { it.yemek_siparis_adet }
            val firstItem = entry.value.first()
            val sepetYemekIdList = entry.value.map { it.sepet_yemek_id }.toMutableList()
            Log.d("CartViewModel", "combineCartItems: $sepetYemekIdList")
            CartItem(
                yemek_adi = firstItem.yemek_adi,
                yemek_resim_adi = firstItem.yemek_resim_adi,
                yemek_fiyat = firstItem.yemek_fiyat.toInt(),
                yemek_siparis_adet = combinedQuantity,
                sepetYemekIdList = sepetYemekIdList
            )
        }
    }

    fun decreaseItemQuantity(cartItem: CartItem) {
        viewModelScope.launch {
            if (cartItem.yemek_siparis_adet > 1) {
                // Miktarı 1 azalt
                cartItem.yemek_siparis_adet -= 1
                // Sunucudan ilgili bir kaydı sil
                val sepetYemekId = cartItem.sepetYemekIdList.last()
                val success = removeFromCartUseCase(sepetYemekId)
                if (success) {
                    // Kayıt ID listesinden kaldır
                    cartItem.sepetYemekIdList.removeAt(cartItem.sepetYemekIdList.lastIndex)
                    // Sepet öğelerini güncelle
                    getCartItems()
                } else {
                    // Hata yönetimi
                    Log.e("CartViewModel", "Miktar azaltılamadı")
                }
            } else {
                // Miktar 1 ise, öğeyi tamamen kaldır
                removeItemCompletely(cartItem)
            }
        }
    }


    fun removeItemCompletely(cartItem: CartItem) {
        viewModelScope.launch {
            try {
                var success = true
                cartItem.sepetYemekIdList.forEach { sepetYemekId ->
                    val result = removeFromCartUseCase(sepetYemekId)
                    if (!result) {
                        success = false
                    }
                }
                if (success) {
                    getCartItems()
                } else {
                    // Hata yönetimi
                    Log.e("CartViewModel", "Öğe tamamen silinemedi")
                }
            } catch (e: Exception) {
                Log.e("CartViewModel", "Hata oluştu: ${e.message}")
            }
        }
    }

    fun addToCart(food: Food, quantity: Int) {
        viewModelScope.launch {
            try {
                val success = addToCartUseCase(food, quantity)
                if (success) {
                    Log.e("CartViewModel", "${food.name} sepete eklendi.")
                } else
                {
                    Log.e("CartViewModel", "Sepete ekleme başarısız: ${food.name}")
                }
            } catch (e: java.lang.Exception) {
                Log.e("CartViewModel", "Hata oluştu: ${e.message}")
            }
        }
    }

    val totalPrice: StateFlow<Int> = _cartItems.map { items ->
        items.sumOf { it.yemek_fiyat.toInt() * it.yemek_siparis_adet }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, 0)
}

