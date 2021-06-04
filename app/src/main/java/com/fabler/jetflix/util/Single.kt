package com.fabler.jetflix.util

import com.fabler.jetflix.util.Single.Error
import com.fabler.jetflix.util.Single.Success

fun <T> success(s: T) = Success(s)
fun <T> error(e: T) = Error(e)

sealed class Single<out E, out S> {
  data class Error<out E>(val e: E) : Single<E, Nothing>()
  data class Success<out S>(val s: S) : Single<Nothing, S>()

  fun isError(): Boolean = this is Error
  fun isSuccess(): Boolean = this is Success

  fun <T> subscribe(fnL: (E) -> T, fnR: (S) -> T): T {
    return when (this) {
      is Error -> fnL(e)
      is Success -> fnR(s)
    }
  }

  fun getOrNull(): S? {
    return if (this is Success) {
      s
    } else {
      null
    }
  }
}

fun <R, T : R> Single<Any, T>.getOrElse(defaultValue: R): R {
  return when (this) {
    is Success -> s
    else -> defaultValue
  }
}