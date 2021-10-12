package com.example.myapplication.presentation.utils.extentions

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.showToastWithErrorMessage(@StringRes textId: Int){
    Toast.makeText(this, this.getString(textId), Toast.LENGTH_LONG).show()
}