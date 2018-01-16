package com.engineeringidea.mysampleapp.data_layer.requests

import com.eidea.testapplication.data_layer.api.SignInApi
import com.eidea.testapplication.data_layer.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInRequest(private val user: User) : BaseRequest() {

    private val service = retrofit.create(SignInApi::class.java)

    fun resumeWithCompletionHandler(handler: RequestCompletionHandler<User>) {

        val headers = HashMap<String, String>()
        headers.put(BaseRequest.Keys.ACCEPT.value, BaseRequest.Values.APP_JSON.value)
        headers.put(BaseRequest.Keys.CONTENT_TYPE.value, BaseRequest.Values.APP_JSON.value)

//        val call = service.signIn(headers, Login(user))
        val call = service.signIn(headers)

        call.enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.body() != null) {
                    val user = response.body()
                    handler.completionHandler(user)
                } else {
                    handler.completionHandlerWithError("Response is empty")
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                handler.completionHandlerWithError(t.message)
            }
        })
    }
}
