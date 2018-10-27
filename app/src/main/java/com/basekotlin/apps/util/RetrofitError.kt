package com.basekotlin.apps.util

import com.basekotlin.apps.data.model.ApiResponse

/**
 * Created by test(test@gmail.com) on 12/4/16.
 */

object RetrofitError {

    fun handleError(e: Throwable): String? {
        try {
            val error = e as RetrofitException
            val response = error.getErrorBodyAs(ApiResponse::class.java)

            return if (response != null) {
                response.meta!!.message
            } else {
                "Network Error, Please Try Again"
            }
        } catch (ex: Exception) {
            return e.message
        }

    }
}

