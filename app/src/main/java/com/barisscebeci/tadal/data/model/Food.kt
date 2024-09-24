package com.barisscebeci.tadal.data.model

import com.google.gson.annotations.SerializedName

data class Food(
    @SerializedName("yemek_id")
    val id: String,   //String veri geliyor
    @SerializedName("yemek_adi")
    var name: String,
    @SerializedName("yemek_resim_adi")
    var imageName: String,
    @SerializedName("yemek_fiyat")
    var price: String   //String veri geliyor
) {
    // Firestore için parametresiz yapıcı
    constructor() : this("", "", "", "")

    val imageUrl: String
        //Burada yapılan işlem get metodu ile çekilecek resim sitesine istek yolu yapılıyor
        get() = "http://kasimadalan.pe.hu/yemekler/resimler/$imageName"
}