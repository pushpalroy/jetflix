package com.fabler.jetflix.ui.dashboard.home.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabler.jetflix.data.network.constant.MoviesApi
import com.fabler.jetflix.domain.model.Movie
import com.fabler.jetflix.domain.repo.MoviesRepository
import com.fabler.jetflix.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularMoviesViewModel @Inject constructor(
  private val repository: MoviesRepository
) : ViewModel() {

  var popularMovies by mutableStateOf<Resource<List<Movie>>>(Resource.Loading)
    private set

  init {
    fetchPopularMovies()
  }

  fun getPopularMovies(): List<Movie>? {
    return when (popularMovies) {
      is Resource.Error -> null
      Resource.Loading -> null
      is Resource.Success -> (popularMovies as Resource.Success<List<Movie>>).data
    }
  }

  fun getPopularMovieDetails(id: Long): Movie? {
    return when (popularMovies) {
      is Resource.Error -> null
      Resource.Loading -> null
      is Resource.Success -> (popularMovies as Resource.Success<List<Movie>>).data.find { it.id == id }
    }
  }

  private fun fetchPopularMovies() {
    viewModelScope.launch {
      popularMovies = Resource.Loading
      repository.getPopularMovies(language = MoviesApi.LANG_ENG, page = (0..5).random())
        .subscribe(
          { error ->
            popularMovies = Resource.Error(error)
          },
          { data ->
            popularMovies = Resource.Success(data)
          }
        )
    }
  }
}