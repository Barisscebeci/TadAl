package com.barisscebeci.tadal.data.repository

import android.util.Log
import com.barisscebeci.tadal.data.model.Cart
import com.barisscebeci.tadal.data.remote.FoodDataSource
import com.barisscebeci.tadal.data.model.Food
import com.barisscebeci.tadal.data.model.Rating
import com.barisscebeci.tadal.presentation.ui.screen.auth.UserSession
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FoodRepository @Inject constructor(
    private val dataSource: FoodDataSource,
    private val firestore: FirebaseFirestore
) {

    //Tüm yemekleri getirme işlemi
    suspend fun getAllFoods(): List<Food> {
        val response = dataSource.getAllFoods()
        return if (response.success == 1) {
            response.foods
        } else {
            emptyList()
        }
    }

    //Sepete yemek ekleme işlemi
    suspend fun addToCart(
        food: Food,
        quantity: Int,
    ): Boolean {
        val kullaniciAdi = UserSession.username
            ?: "misafir_kullanici"    //Eğer kullanici Giriş yapmadan sepete ekleme işlemi yaparsa misafir_kullanici adi ile yapacak
        val response = dataSource.addToCart(
            yemekAdi = food.name,
            yemekResimAdi = food.imageName,
            yemekFiyat = food.price.toInt(),  //bana yemekfiyat ile bir int değer döndürüyorsun fakat price food classında string bir parametre bu yüzden tür dönüşümü yapıyoruz
            yemekSiparisAdet = quantity,
            kullaniciAdi = kullaniciAdi
        )
        return response.success == 1
    }

    //Sepetteki yemekleri getirme işlemi
    suspend fun getCartItems(): List<Cart> {
        return try {
            val kullaniciAdi = UserSession.username ?: "misafir_kullanici"
            val response = dataSource.getCartItems(kullaniciAdi)
            if (response.success == 1) {
                response.sepet_yemekler ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            Log.e("FoodRepository", "Sepet öğeleri alınırken hata oluştu: ${e.message}")
            emptyList()
        }
    }

    //Sepetten yemek silme işlemleri
    suspend fun removeFromCart(sepetYemekId: Int): Boolean {
        val kullaniciAdi = UserSession.username ?: "misafir_kullanici"
        val response = dataSource.removeFromCart(sepetYemekId, kullaniciAdi)
        return if (response.isSuccessful) {
            response.body()?.success == 1
        } else {
            false
        }
    }

    //Favori yemek ekleme işlemi
    suspend fun addToFavorites(food: Food): Boolean {
        return try {
            val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return false
            firestore.collection("users")
                .document(userId)
                .collection("favorites")
                .document(food.id.toString())
                .set(food)
                .await()
            Log.d("FoodRepository", "Ürün favorilere eklendi: ${food.name}")
            true
        } catch (e: Exception) {
            Log.e("FoodRepository", "Favorilere ekleme hatası: ${e.message}")
            false
        }
    }

    //Favoriden yemek silme işlemi
    suspend fun removeFromFavorites(food: Food) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
        firestore.collection("users")
            .document(userId)
            .collection("favorites")
            .document(food.id.toString())
            .delete()
            .await()
    }

    //Favori yemekleri getirme işlemi
    suspend fun getFavoriteFoods(): List<Food> {
        return try {
            val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return emptyList()
            val querySnapshot = firestore.collection("users")
                .document(userId)
                .collection("favorites")
                .get()
                .await()
            querySnapshot.documents.mapNotNull { it.toObject(Food::class.java) }
        } catch (e: Exception) {
            Log.e("FoodRepository", "Favori ürünler alınırken hata oluştu: ${e.message}")
            emptyList()
        }
    }

    suspend fun querySearch(query: String): List<Food> {
        val allFoods = getAllFoods()
        return allFoods.filter { food ->
            food.name.contains(query, ignoreCase = true)
        }
    }

    //Rating verilerini kaydetme
    suspend fun saveRating(foodId: String, rating: Float) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
        val ratingData = Rating(userId, rating)
        firestore.collection("ratings")
            .document(userId)
            .collection("ratings")
            .document(foodId)
            .set(ratingData)
            .await()
    }

    //Rating verilerini getirme
    suspend fun getRating(foodId: String): Float {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return 0f
        val snapshot = firestore.collection("ratings")
            .document(userId)
            .collection("ratings")
            .document(foodId)
            .get()
            .await()
        return snapshot.getDouble("rating")?.toFloat() ?: 0f
    }
}