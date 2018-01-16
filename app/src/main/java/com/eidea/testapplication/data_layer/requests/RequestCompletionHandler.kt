package com.engineeringidea.mysampleapp.data_layer.requests

interface RequestCompletionHandler<in T> {
    fun completionHandler(data: T)

    fun completionHandlerWithError(error: String?)
}