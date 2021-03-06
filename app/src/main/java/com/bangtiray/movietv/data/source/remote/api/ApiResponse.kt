package com.bangtiray.movietv.data.source.remote.api

import com.bangtiray.movietv.data.source.Status

class ApiResponse<T>(val status: Status, val body: T?, val message: String?) {
    companion object {
        fun <T> success(body: T): ApiResponse<T> = ApiResponse(Status.SUCCESS, body, null)

        fun <T> error(msg: String, body: T): ApiResponse<T> = ApiResponse(Status.ERROR, body, msg)
    }
}