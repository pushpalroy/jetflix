package com.fabler.jetflix.ui.moviedetail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabler.jetflix.domain.repo.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieVideosViewModel @Inject constructor(
  private val repository: MoviesRepository
) : ViewModel() {

  private val _movieVideo = MutableLiveData("")
  val movieVideo: LiveData<String> = _movieVideo

  fun fetchMovieVideoById(movieId: Long) {
    viewModelScope.launch {
      repository.getMovieVideosById(movieId = movieId)
        .subscribe(
          {},
          { data ->
            _movieVideo.value = data.url
          }
        )
    }
  }
}