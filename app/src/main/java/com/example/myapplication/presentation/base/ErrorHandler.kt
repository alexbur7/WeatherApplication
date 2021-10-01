package com.example.myapplication.presentation.base

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import com.example.myapplication.R
import retrofit2.HttpException
import javax.inject.Inject

class ErrorHandler @Inject constructor(
    private val context: Context
) {

    fun showErrorToast(throwable: Throwable){
        when (throwable) {
            is HttpException -> {
                if (throwable.code() == 404){
                    showToast(R.string.unknown_city)
                }
                else {
                    showToast(R.string.server_error)
                }
            }
            else -> {
                showToast(R.string.connection_error)
            }
        }
    }

    private fun showToast(@StringRes textId: Int) {
        Toast.makeText(context, textId, Toast.LENGTH_SHORT).show()
    }
}