package com.wpy.wanandroid.base.net

import com.shehuan.wanandroid.base.net.interceptor.AddCookiesInterceptor
import com.shehuan.wanandroid.base.net.interceptor.SaveCookiesInterceptor
import com.wpy.wanandroid.base.Constant
import com.wpy.wanandroid.base.net.interceptor.NetLogInterceptor
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
            .baseUrl(Constant.WAN_ANDROID_URL)
            .build()

        return retrofit.create(service)
    }

    private fun getOkHttpClient(flag: Boolean): OkHttpClient {
        val buidler = OkHttpClient.Builder().apply {
            connectTimeout(Constant.HTTP_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(Constant.HTTP_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(Constant.HTTP_TIMEOUT, TimeUnit.SECONDS)
//            if (flag) {
//                val loggingInterceptor = HttpLoggingInterceptor()
//                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
//                addInterceptor(loggingInterceptor)
//            }
            addInterceptor(NetLogInterceptor(flag))//设置网络日志
            // 请求相应拦截器
            addInterceptor(SaveCookiesInterceptor())
            addInterceptor(AddCookiesInterceptor())
        }
        return buidler.build()
    }
}