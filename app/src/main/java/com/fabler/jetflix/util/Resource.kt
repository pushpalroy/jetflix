package com.fabler.jetflix.util

sealed class Resource<out T> {
  data class Success<out T>(val data: T) : Resource<T>()
  data class Error(val error: com.fabler.jetflix.util.Error) : Resource<Nothing>()
  object Loading : Resource<Nothing>()
}