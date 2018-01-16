package com.engineeringidea.mysampleapp.data_layer.requests

import com.eidea.testapplication.data_layer.AppScope
import retrofit2.Retrofit

abstract class BaseRequest {

    protected val retrofit: Retrofit = AppScope.retrofit

    enum class Keys constructor(val value: String) {
        ACCEPT("Accept"),
        CONTENT_TYPE("Content-Type"),
        SESSION_KEY("session-key")
    }

    enum class Values constructor(val value: String) {
        APP_JSON("application/json")
    }
}
