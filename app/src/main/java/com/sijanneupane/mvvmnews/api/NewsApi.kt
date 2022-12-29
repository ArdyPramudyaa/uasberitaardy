package com.sijanneupane.mvvmnews.api

import com.sijanneupane.mvvmnews.models.NewsResponse
import com.sijanneupane.mvvmnews.utils.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    /* di sini kita mendefinisikan satu permintaan yang dapat kita jalankan dari kode
     */
    //kami menggunakan antarmuka Api untuk mengakses api untuk permintaan
    //berfungsi untuk mendapatkan semua berita terkini dari api
    // kita perlu menentukan jenis permintaan http -GET di sini
    //dan kami mengembalikan respons dari API

    @GET("v2/top-headlines")

    //function
    //async
    //coroutine
    suspend fun getBreakingNews(
        //request parameters to function
    @Query("country")
    countryCode: String = "us", //default to us

    @Query("page")  //to paginate the request
    pageNumber: Int= 1,

    @Query("apiKey")
    apiKey: String= API_KEY

    ):Response<NewsResponse> //return response


    @GET("v2/everything")

    //function
    //async
    //coroutine
    suspend fun searchForNews(
        //request parameters to function
        @Query("q")
        searchQuery: String,
        @Query("page")  //to paginate the request
        pageNumber: Int= 1,
        @Query("apiKey")
        apiKey: String= API_KEY
    ):Response<NewsResponse> //return response
}