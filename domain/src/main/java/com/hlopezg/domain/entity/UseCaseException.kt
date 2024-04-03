package com.hlopezg.domain.entity

import android.util.Log

sealed class UseCaseException(cause: Throwable) : Throwable(cause) {

    class MovieException(cause: Throwable) : UseCaseException(cause)

    class TvException(cause: Throwable) : UseCaseException(cause)

    class UnknownException(cause: Throwable) : UseCaseException(cause)


    companion object {

        fun createFromThrowable(throwable: Throwable): UseCaseException {
            Log.i("USeCaseException", throwable.localizedMessage)
            return if (throwable is UseCaseException) throwable else UnknownException(throwable)
        }
    }
}