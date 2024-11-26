package com.john.core.models

sealed interface ResultWrapper<T> {
    data class Success<T>(val data: T) : ResultWrapper<T>
    data class TimeoutException<T>(val message: String?) : ResultWrapper<T>
    data class NetworkException<T>(val message: String?) : ResultWrapper<T>
    data class UnknownException<T>(val message: String?) : ResultWrapper<T>
}