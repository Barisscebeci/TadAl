package com.barisscebeci.tadal.data.remote

import com.barisscebeci.tadal.data.model.CartResponse
import com.barisscebeci.tadal.data.model.FoodResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface FoodApiService {
    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun getAllFoods(): FoodResponse

    @FormUrlEncoded
    @POST("yemekler/sepeteYemekEkle.php")
    suspend fun AddToCart(
        @Field("yemek_adi") yemekAdi: String,
        @Field("yemek_resim_adi") yemekResimAdi: String,
        @Field("yemek_fiyat") yemekFiyat: Int,
        @Field("yemek_siparis_adet") yemekSiparisAdet: Int,
        @Field("kullanici_adi") kullaniciAdi: String
    ): CartResponse

    @FormUrlEncoded
    @POST("yemekler/sepettekiYemekleriGetir.php")
    suspend fun getCartItems(
        @Field("kullanici_adi") kullaniciAdi: String
    ): CartResponse

    @FormUrlEncoded
    @POST("yemekler/sepettenYemekSil.php")
    suspend fun removeFromCart(
        @Field("sepet_yemek_id") sepetYemekId: Int,
        @Field("kullanici_adi") kullaniciAdi: String
    ): Response<CartResponse>


}
