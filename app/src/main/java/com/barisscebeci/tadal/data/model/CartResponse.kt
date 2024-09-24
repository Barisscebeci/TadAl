package com.barisscebeci.tadal.data.model

data class CartResponse(
    val sepet_yemekler: List<Cart>?,
    val success: Int,
    val message: String?
) {
}