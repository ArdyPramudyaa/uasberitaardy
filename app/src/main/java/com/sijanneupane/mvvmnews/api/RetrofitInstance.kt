package com.sijanneupane.mvvmnews.api

import com.sijanneupane.mvvmnews.utils.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*
    kelas Instans Retrofit ini memungkinkan kita membuat permintaan dari mana saja dalam kode kita

 */
class RetrofitInstance {
    companion object{
        private val  retrofit by lazy {
            //menginisialisasi

            val logging= HttpLoggingInterceptor()
            /* dependensi HTTP LOGGING INTERCEPTOR ini dapat mencatat respons retrofit
            ini akan berguna dalam men-debug kode
             */
            // melampirkan ke objek retrofit
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)  //see the body of the response
            //network client
            val client= OkHttpClient.Builder().addInterceptor(logging).build()

            //pass the client to retrofit instance
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
        //addConverterFactory is use to determine how the response should be interpreted and converted to kotlin object
                .client(client)
                .build()


        }

        //dapatkan instance api dari pembuat retrofit
        // objek api
        // ini dapat digunakan dari mana saja untuk membuat permintaan jaringan
        val api by lazy {
            retrofit.create(NewsApi::class.java)
        }
    }
}