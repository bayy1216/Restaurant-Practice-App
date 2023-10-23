package com.reditus.restaurant_practice_app.core.di

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import com.reditus.restaurant_practice_app.data.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton



class HeaderInterceptor @Inject constructor(
    private val prefs: SharedPreferences
) : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        if(chain.request().headers["Auth"] == "false"){
            val newRequest = chain.request().newBuilder()
                .removeHeader("Auth")
                .build()
            return chain.proceed(newRequest)
        }

        var token = ""
        runBlocking {
            token = ("Bearer " + prefs.getString("accessToken", ""))
            Log.d("token", token)
        }
        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", token)
            .build()
        val response = chain.proceed(newRequest)

        //accessToken이 만료되었을 때
        if(response.code == 401){
            response.close()
            val x = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)
            val okHttp = OkHttpClient.Builder().addInterceptor(x).build()

            val refreshToken = runBlocking {
                prefs.getString("refreshToken", "")
            }

            val refreshRequest = Request.Builder()
                .url("https://restaurant-app.run.goorm.site/auth/token")
                .post("".toRequestBody())
                .addHeader("authorization", "Bearer ${refreshToken!!}")
                .build()

            val refreshResponse = okHttp.newCall(refreshRequest).execute()

            if(refreshResponse.code == 201){
                val gson = Gson()
                val refreshResponseJson = gson.fromJson(refreshResponse.body?.string(), Map::class.java)
                val newAccessToken = refreshResponseJson["accessToken"].toString()
                prefs.edit().putString("accessToken", newAccessToken).apply()
                val originRequest = chain.request().newBuilder()
                    .removeHeader("Authorization")
                    .addHeader("Authorization", "Bearer $newAccessToken")
                    .build()
                return chain.proceed(originRequest)
            }else{
                //TODO: 로그아웃 처리
                prefs.edit().remove("accessToken").apply()
                prefs.edit().remove("refreshToken").apply()
            }
        }


        return response
    }
}


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideHeaderInterceptor(autoLoginPreferences: SharedPreferences): HeaderInterceptor {
        return HeaderInterceptor(autoLoginPreferences)
    }
    @Singleton
    @Provides
    fun provideInterceptor(): Interceptor {
        return HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(headerInterceptor: HeaderInterceptor): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(headerInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): Converter.Factory{
        return GsonConverterFactory.create()
    }
    @Singleton
    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
            .baseUrl("https://restaurant-app.run.goorm.site")
            .build()
    }

    @Singleton
    @Provides
    fun providerApi(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}