package com.example.tinker.client

import com.example.tinker.data.UntappdBeerData
import com.example.tinker.data.UntappdData
import com.example.tinker.data.UntappdDetailData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UntappdApi {
    @GET("brewery/info/{brewery}")
    suspend fun getBrewery(@Path("brewery") brewery: Int = 1149, @Query("client_id") client_id: String, @Query("client_secret") client_secret: String) : UntappdDetailData

    @GET("search/brewery")
    suspend fun searchBrewery(@Query("q") q: String, @Query("client_id") client_id: String, @Query("client_secret") client_secret: String) : UntappdData

    @GET("beer/info/{BID}")
    suspend fun getBeer(@Path("BID") BID: Int, @Query("client_id") client_id: String, @Query("client_secret") client_secret: String, @Query("compact") compact: Boolean = true) : UntappdBeerData

    companion object{
        val retrofit : Retrofit = Retrofit.Builder().baseUrl("https://api.untappd.com/v4/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val service : UntappdApi = retrofit.create<UntappdApi>(UntappdApi::class.java)
    }
}