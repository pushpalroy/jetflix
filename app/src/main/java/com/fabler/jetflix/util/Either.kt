package com.fabler.jetflix.util

import com.fabler.jetflix.util.Either.Error
import com.fabler.jetflix.util.Either.Success

fun <T> success(r: T) = Success(r)
fun <T> error(l: T) = Error(l)

sealed class Either<out L, out R> {
  data class Error<out L>(val l: L) : Either<L, Nothing>()
  data class Success<out R>(val r: R) : Either<Nothing, R>()

  fun isLeft(): Boolean = this is Error
  fun isRight(): Boolean = this is Success

  fun <T> fold(fnL: (L) -> T, fnR: (R) -> T): T {
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

fun <R, T : R> Either<Any, T>.getOrElse(defaultValue: R): R {
  return when (this) {
    is Success -> r
    else -> defaultValue
  }
}