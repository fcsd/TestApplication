package com.eidea.testapplication.data_layer

import com.eidea.testapplication.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieManager
import java.net.CookiePolicy
import java.util.concurrent.TimeUnit

object AppScope {

    private val accountController = AccountController()

    val retrofit: Retrofit by lazy { initRetrofit() }

    fun getAccountController(): AccountController {
        return accountController
    }

    private fun initRetrofit(): Retrofit {
        val cookieManager = CookieManager()
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL)
        val client = OkHttpClient.Builder()
                .connectTimeout(Constants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(Constants.WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(Constants.READ_TIMEOUT, TimeUnit.SECONDS)
                .build()

        return Retrofit.Builder()
                .baseUrl(Constants.HOST)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
}