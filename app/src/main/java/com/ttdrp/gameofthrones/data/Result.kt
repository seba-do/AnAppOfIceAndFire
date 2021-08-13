package com.ttdrp.gameofthrones.data

sealed class Result<out R> {
    object Uninitialized : Result<Nothing>()
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}