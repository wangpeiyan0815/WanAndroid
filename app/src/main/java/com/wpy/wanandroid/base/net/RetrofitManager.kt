package com.wpy.wanandroid.base.net

import com.wpy.wanandroid.base.Const
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitManager {

    private val okHttpClient: OkHttpClient by lazy {
        getOkHttpClient(true)
    }

    fun <S> create(service: Class<S>): S {
        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(Const.WAN_ANDROID_URL)
            .build()

        return retrofit.create(service)
    }

    private fun getOkHttpClient(flag: Boolean): OkHttpClient {
        val buidler = OkHttpClient.Builder().apply {
            connectTimeout(10, TimeUnit.SECONDS)
            writeTimeout(10, TimeUnit.SECONDS)
            readTimeout(10, TimeUnit.SECONDS)

            if (flag) {
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                addInterceptor(loggingInterceptor)
            }

            // 请求相应拦截器

        }
        return buidler.build()
    }
}