package com.example.hiltsample

import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.google.gson.JsonSyntaxException
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    const val BASE_URL = "https://2020capi.census.go.kr/mCAPI/and2020/"

    @Provides
    @Singleton
    fun provideBaseUrl() = BASE_URL

    @Provides
    @Singleton
    fun httpLoggingInterceptor(): HttpLoggingInterceptor =
        //HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                if (!message.startsWith("{") && !message.startsWith("[")) {
                    Timber.tag("OkHttp").d(message)
                    return
                }
                try {
                    // Timber 와 Gson setPrettyPrinting 를 이용해 json 을 보기 편하게 표시해준다.
                    Timber.tag("OkHttp")
                        .d(GsonBuilder().setPrettyPrinting().create().toJson(JsonParser().parse(message)))
                } catch (m: JsonSyntaxException) {
                    Timber.tag("OkHttp").d(message)
                }
            }

        }).apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor).connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}