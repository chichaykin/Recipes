package com.chichaykin.recipes.network


import com.chichaykin.recipes.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkModule {

    private val AUTHORIZE_INTERCEPTOR = Interceptor { chain ->
        val request = chain.request()
        val newRequest: Request

        newRequest = request.newBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("key", BuildConfig.API_KEY)
                .build()
        chain.proceed(newRequest)
    }

    fun buildNetworkApi(): RecipeApi {
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.networkInterceptors().add(AUTHORIZE_INTERCEPTOR)

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.interceptors().add(logging)
        }

        return Retrofit.Builder().client(clientBuilder.build())
                .baseUrl(BuildConfig.API_HOST)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RecipeApi::class.java)

    }
}
