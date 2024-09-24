package com.barisscebeci.tadal.di

import com.barisscebeci.tadal.data.remote.FoodApiService
import com.barisscebeci.tadal.data.remote.FoodDataSource
import com.barisscebeci.tadal.core.network.RetrofitClient
import com.barisscebeci.tadal.data.repository.FoodRepository
import com.barisscebeci.tadal.domain.usecase.AddToCartUseCase
import com.barisscebeci.tadal.domain.usecase.AddToFavoritesUseCase
import com.barisscebeci.tadal.domain.usecase.GetAllFoodsUseCase
import com.barisscebeci.tadal.domain.usecase.GetCartItemsUseCase
import com.barisscebeci.tadal.domain.usecase.GetFavoritesFoods
import com.barisscebeci.tadal.domain.usecase.GetRatingUseCase
import com.barisscebeci.tadal.domain.usecase.QuerySearch
import com.barisscebeci.tadal.domain.usecase.RemoveFromCartUseCase
import com.barisscebeci.tadal.domain.usecase.RemoveFromFavoritesUseCase
import com.barisscebeci.tadal.domain.usecase.SaveRatingUseCase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule{

    //RetrofitClient sınıfından apiService nesnesini döndüren fonksiyon
    @Singleton
    @Provides
    fun provideFoodApiService(): FoodApiService {
        return RetrofitClient.apiService
    }

    //FoodDataSource sınıfından nesne döndüren fonksiyon
    @Singleton
    @Provides
    fun provideFoodDataSource(apiService: FoodApiService): FoodDataSource {
        return FoodDataSource(apiService)
    }

    // FirebaseAuth sağlayıcı fonksiyonu
    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    // FirebaseFirestore sağlayıcı fonksiyonu
    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    // FoodRepository sınıfından nesne döndüren fonksiyon
    @Singleton
    @Provides
    fun provideFoodRepository(dataSource: FoodDataSource, firestore: FirebaseFirestore): FoodRepository {
        return FoodRepository(dataSource, firestore)
    }

    @Provides
    fun provideAddToCartUseCase(repository: FoodRepository): AddToCartUseCase {
        return AddToCartUseCase(repository)
    }

    @Provides
    fun provideGetAllFoodsUseCase(repository: FoodRepository): GetAllFoodsUseCase {
        return GetAllFoodsUseCase(repository)
    }

    @Provides
    fun provideGetCartItemsUseCase(repository: FoodRepository): GetCartItemsUseCase {
        return GetCartItemsUseCase(repository)
    }

    @Provides
    fun provideRemoveFromCartUseCase(repository: FoodRepository): RemoveFromCartUseCase {
        return RemoveFromCartUseCase(repository)
    }

    @Provides
    fun provideRemoveFromFavoritesUseCase(repository: FoodRepository): RemoveFromFavoritesUseCase {
        return RemoveFromFavoritesUseCase(repository)
    }

    @Provides
    fun provideAddToFavoritesUseCase(repository: FoodRepository): AddToFavoritesUseCase {
        return AddToFavoritesUseCase(repository)
    }

    @Provides
    fun provideGetFavoritesFoodsUseCase(repository: FoodRepository): GetFavoritesFoods {
        return GetFavoritesFoods(repository)
    }

    @Provides
    fun provideQuerySearchUseCase(repository: FoodRepository): QuerySearch {
        return QuerySearch(repository)
    }

    @Provides
    fun provideSaveRatingUseCase(repository: FoodRepository): SaveRatingUseCase {
        return SaveRatingUseCase(repository)
    }

    @Provides
    fun provideGetRatingUseCase(repository: FoodRepository): GetRatingUseCase {
        return GetRatingUseCase(repository)
    }

}