package com.eidea.testapplication.data_layer.model

import com.google.gson.annotations.SerializedName

class User {
    @SerializedName("authentication_token")
    public var authToken: String? = null
    @SerializedName("email")
    public var email: String? = null
    @SerializedName("first_name")
    public var firstName: String? = null
    @SerializedName("password")
    public var password: String? = null
}