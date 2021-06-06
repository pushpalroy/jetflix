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
class SelectedMovieViewModel @Inject constructor(
  val repository: MoviesRepository
) : ViewModel() {

  var selectedMovie by mutableStateOf<Resource<Movie?>>(Loading)
    private set

  var similarMovies by mutableStateOf<Resource<List<Movie?>>>(Loading)
    private set

  fun setSelectedMovie(movie: Movie?) {
    selectedMovie = Resource.Success(movie)
  }

  fun fetchSimilarMovies(movieId: Long) {
    viewModelScope.launch {
      similarMovies = Loading
      repository.getSimilarMovies(movieId)
        .subscribe(
          { error ->
            similarMovies = Resource.Error(error)
          },
          { data ->
            similarMovies = Resource.Success(data)
          }
        )
    }
  }
}