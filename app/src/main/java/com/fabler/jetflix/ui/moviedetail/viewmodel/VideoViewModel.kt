package com.fabler.jetflix.ui.moviedetail.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.util.SparseArray
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fabler.jetflix.data.network.constant.MoviesApi.SAMPLE_VIDEO_URL
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor() : ViewModel() {

  private val _extractedVideoUrl = MutableLiveData("")
  val extractedVideoUrl: LiveData<String> = _extractedVideoUrl

  @SuppressLint("StaticFieldLeak")
  fun extract(context: Context, youtubeLink: String) {

    object : com.fabler.youtubeextractor.YouTubeExtractor(context) {
      override fun onExtractionComplete(ytFiles: SparseArray<com.fabler.youtubeextractor.YtFile>?, vMeta: com.fabler.youtubeextractor.VideoMeta?) {
        if (ytFiles != null) {
          val iTag = 22
          val streamUrl = ytFiles[iTag]?.url

          streamUrl?.let { safeStreamUrl ->
            _extractedVideoUrl.value = safeStreamUrl
          }
        } else {
          _extractedVideoUrl.value = SAMPLE_VIDEO_URL
        }
      }
    }.extract(youtubeLink, false, false)
  }
}