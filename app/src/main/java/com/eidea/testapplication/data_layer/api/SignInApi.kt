package com.eidea.testapplication.data_layer.api

import com.eidea.testapplication.data_layer.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.HeaderMap

interface SignInApi {
    @GET("59c37afc120000da089c0d8f/")
    fun signIn(@HeaderMap headers: Map<String, String>): Call<User>
}