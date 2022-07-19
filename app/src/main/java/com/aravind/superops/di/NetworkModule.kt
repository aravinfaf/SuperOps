package com.aravind.superops.di

import com.aravind.superops.data.api.ApiService
import com.aravind.superops.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideLoggingInterceptor() : HttpLoggingInterceptor{
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor) : OkHttpClient{
        return OkHttpClient().newBuilder().addNetworkInterceptor(loggingInterceptor).build()
    }

    @Provides
    fun provideRetrofitInstance(client: OkHttpClient) : Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideRetrofitService(retrofit: Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java)
    }
}