package com.skilledhands.recyclerviewmvvmrxjava

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    val BASE_URL="https://jsonplaceholder.typicode.com/"
    var retrofit: Retrofit? =null

    val apiClient: Retrofit?
        get() {
            if (retrofit == null) {

                val retrofitBuilder = Retrofit.Builder()
                val httpClient = OkHttpClient.Builder()

                //see logs for debugging
                if (BuildConfig.DEBUG) {
                    val logging = HttpLoggingInterceptor()
                    logging.level = HttpLoggingInterceptor.Level.BODY
                    httpClient.addInterceptor(logging)
                }
                httpClient.callTimeout(40, TimeUnit.SECONDS)
                val gson = GsonBuilder()
                    .setLenient()
                    .create()

                retrofit = retrofitBuilder.baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(httpClient.build())
                    .build()
            }
            return retrofit
        }

}