package com.fabler.jetflix.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.fabler.jetflix.domain.model.Movie
import com.fabler.jetflix.util.Resource
import com.fabler.jetflix.util.Resource.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectedMovieViewModel @Inject constructor() : ViewModel() {

  var selectedMovie by mutableStateOf<Resource<Movie?>>(Loading)
    private set

  fun setSelectedMovie(movie: Movie?) {
    selectedMovie = Resource.Success(movie)
  }
}