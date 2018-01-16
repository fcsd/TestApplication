package com.eidea.testapplication.data_layer.data_source

import com.eidea.testapplication.data_layer.model.User
import com.engineeringidea.mysampleapp.data_layer.requests.RequestCompletionHandler
import com.engineeringidea.mysampleapp.data_layer.requests.SignInRequest

class SignInDataSource : BaseDataSource<User>() {

    var user: User? = null

    override fun reloadData() {
        user?.let {
            notifyListenersWillLoadItems()

            val signInRequest = SignInRequest(it)

            signInRequest.resumeWithCompletionHandler(object : RequestCompletionHandler<User> {
                override fun completionHandler(data: User) {
                    this@SignInDataSource.data = data
                    notifyListenersDidLoadItems()
                }

                override fun completionHandlerWithError(error: String?) {
                    notifyListenersDidLoadItemsWithError(error)
                }
            })
        }
    }
}