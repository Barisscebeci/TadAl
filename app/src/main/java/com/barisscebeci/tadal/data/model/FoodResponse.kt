package com.barisscebeci.tadal.data.model

import com.google.gson.annotations.SerializedName

data class FoodResponse(
    //@SerializedName("yemekler") ifadesi aşağıda bulunan veri tipine bunu eşler yani ne demek istiyorum yemekler değişkeni ile foods değişkenini eşle
    @SerializedName("yemekler")
    val foods: List<Food>,
    @SerializedName("success")
    val success: Int
)