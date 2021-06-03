package com.fabler.jetflix.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabler.jetflix.domain.model.Movie
import com.fabler.jetflix.domain.repo.MoviesRepository
import com.fabler.jetflix.util.Resource
import com.fabler.jetflix.util.Resource.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieByIdViewModel @Inject constructor(
  private val repository: MoviesRepository
) : ViewModel() {

  var movie by mutableStateOf<Resource<Movie>>(Loading)
    private set

  init {
    fetchMovieById(movieId = 503736)
  }

  private fun fetchMovieById(movieId: Long) {
    viewModelScope.launch {
      movie = Resource.Loading
      repository.getMovieById(movieId = movieId)
        .subscribe(
          { error ->
            movie = Resource.Error(error)
          },
          { data ->
            movie = Resource.Success(data)
          }
        )
    }
  }
}