package com.fabler.jetflix.util

import com.fabler.jetflix.util.Single.Error
import com.fabler.jetflix.util.Single.Success

fun <T> success(r: T) = Success(r)
fun <T> error(l: T) = Error(l)

sealed class Single<out L, out R> {
  data class Error<out L>(val l: L) : Single<L, Nothing>()
  data class Success<out R>(val r: R) : Single<Nothing, R>()

  fun isError(): Boolean = this is Error
  fun isSuccess(): Boolean = this is Success

  fun <T> subscribe(fnL: (L) -> T, fnR: (R) -> T): T {
    return when (this) {
      is Error -> fnL(l)
      is Success -> fnR(r)
    }
  }

  fun getOrNull(): R? {
    return if (this is Success) {
      r
    } else {
      null
    }
  }
}

fun <R, T : R> Single<Any, T>.getOrElse(defaultValue: R): R {
  return when (this) {
    is Success -> r
    else -> defaultValue
  }
}