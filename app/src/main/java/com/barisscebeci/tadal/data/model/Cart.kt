package com.barisscebeci.tadal.data.model

data class Cart (
    val sepet_yemek_id: Int,
    val yemek_adi: String,
    val yemek_resim_adi: String,
    val yemek_fiyat: String,
    val yemek_siparis_adet: Int,
    val kullanici_adi: String
){
}