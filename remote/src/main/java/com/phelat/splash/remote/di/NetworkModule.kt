package com.phelat.splash.remote.di

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.phelat.splash.remote.BuildConfig
import com.phelat.splash.remote.di.scopes.ForNetwork
import com.phelat.splash.remote.inceptors.AuthorizationInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by MAHDi on 5/31/18.
 * Contact me m4hdi.pdroid at gmail.com
 */

@Module
class NetworkModule(private val baseUrl: String,
                    private val readTimeout: Long = 30,
                    private val writeTimeout: Long = 60,
                    private val connectTimeout: Long = 30) {

    @Provides
    @ForNetwork
    fun providesAuthorizationInterceptor(): AuthorizationInterceptor {
        return AuthorizationInterceptor()
    }

    @Provides
    @ForNetwork
    fun provideGson(): Gson {
        return GsonBuilder()
                .setFieldNamingStrategy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
    }

    @Provides
    @ForNetwork
    fun provideGsonConvertorFactory(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @ForNetwork
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor { log ->
            if (BuildConfig.DEBUG) {
                // TODO : USE A LOGGER CLASS
                println("OkHttp $log")
            }
        }.setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @ForNetwork
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor,
                            authorizationInterceptor: AuthorizationInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .writeTimeout(writeTimeout, TimeUnit.SECONDS)
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(authorizationInterceptor)
                .build()
    }

    @Provides
    @ForNetwork
    fun provideRetrofit(okHttpClient: OkHttpClient, gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

}