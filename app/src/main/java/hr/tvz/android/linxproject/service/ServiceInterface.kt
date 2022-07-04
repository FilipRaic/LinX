package hr.tvz.android.linxproject.service

import hr.tvz.android.linxproject.model.Product
import retrofit2.Call
import retrofit2.http.GET

interface ServiceInterface {
    @GET("7e109de94c1ff66023c1")
    fun fetchCpuModel(): Call<MutableList<Product>>

    @GET("7f7bad20290901d6ccb5")
    fun fetchGpuModel(): Call<MutableList<Product>>

    @GET("9af7f170a0437a039bfd")
    fun fetchFavoriteModel(): Call<MutableList<Product>>
}