package com.example.myapplication.presentation.utils

import com.example.myapplication.R
import retrofit2.HttpException
import javax.inject.Inject

class ErrorHandler @Inject constructor() {

    fun getErrorStringIdByThrowable(throwable: Throwable): Int{
        return when (throwable) {
            is HttpException -> {
                if (throwable.code() == 404){
                    R.string.unknown_city
                }
                else {
                    R.string.server_error
                }
            }
            else -> {
                R.string.connection_error
            }
        }
    }
}