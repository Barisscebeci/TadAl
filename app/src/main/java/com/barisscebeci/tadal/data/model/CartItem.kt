package com.barisscebeci.tadal.data.model

data class CartItem(
    val yemek_adi: String,
    val yemek_resim_adi: String,
    val yemek_fiyat: Int,
    var yemek_siparis_adet: Int,
    var sepetYemekIdList: MutableList<Int>
) {
    val imageUrl: String
        get() = "http://kasimadalan.pe.hu/yemekler/resimler/$yemek_resim_adi"
}