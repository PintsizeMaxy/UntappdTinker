package com.example.tinker.client

import com.example.tinker.data.UntappdData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UntappdApi {
//    @GET("brewery/info/{brewery}")
//    suspend fun getBrewery(@Path("brewery") brewery: Int = 1149, @Query("client_id") client_id: String, @Query("client_secret") client_secret: String) : BreweryData

    @GET("search/brewery")
    suspend fun searchBrewery(@Query("q") q: String, @Query("client_id") client_id: String, @Query("client_secret") client_secret: String) : UntappdData

    companion object{
        val clientId = "9016D72981246903CAB5DFB42948481B021D651B"
        val clientSecret = "49F57F65DEAD891D8BDB3E5B95615509AB9ECC19"
        val retrofit : Retrofit = Retrofit.Builder().baseUrl("https://api.untappd.com/v4/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val service : UntappdApi = retrofit.create<UntappdApi>(UntappdApi::class.java)
    }
}