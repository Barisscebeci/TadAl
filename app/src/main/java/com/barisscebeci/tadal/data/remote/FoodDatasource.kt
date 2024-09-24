package com.barisscebeci.tadal.data.remote


import com.barisscebeci.tadal.data.model.CartResponse
import com.barisscebeci.tadal.data.model.FoodResponse
import retrofit2.Response
import javax.inject.Inject

class FoodDataSource @Inject constructor(private val apiService: FoodApiService) {
    //Listeleme
    suspend fun getAllFoods(): FoodResponse {
        return apiService.getAllFoods()
    }

    //Sepete Ekleme
    suspend fun addToCart(yemekAdi: String, yemekResimAdi: String, yemekFiyat: Int, yemekSiparisAdet: Int, kullaniciAdi: String
    ): CartResponse {
        return apiService.AddToCart(yemekAdi, yemekResimAdi, yemekFiyat, yemekSiparisAdet, kullaniciAdi
        )
    }

    // Sepetteki yemekleri getirme
    suspend fun getCartItems(kullaniciAdi: String): CartResponse {
        return apiService.getCartItems(kullaniciAdi)
    }

    //Sepetten yemek silme
    suspend fun removeFromCart(sepetYemekId: Int, kullaniciAdi: String): Response<CartResponse> {
        return apiService.removeFromCart(sepetYemekId, kullaniciAdi)
    }

}