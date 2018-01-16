package com.eidea.testapplication.data_layer

import com.eidea.testapplication.data_layer.model.User

class AccountController {

    private var user: User? = null

    fun saveUser(user: User) {
        this.user = user
    }

    fun fetchUser(): User? {
        return user
    }


}