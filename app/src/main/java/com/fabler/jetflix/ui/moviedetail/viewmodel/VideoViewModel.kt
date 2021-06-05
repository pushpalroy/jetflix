package com.fabler.jetflix.ui.moviedetail.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.util.SparseArray
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fabler.jetflix.ui.moviedetail.viewmodel.lib.VideoMeta
import com.fabler.jetflix.ui.moviedetail.viewmodel.lib.YouTubeExtractor
import com.fabler.jetflix.ui.moviedetail.viewmodel.lib.YtFile
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor() : ViewModel() {

  private val _extractedVideoUrl = MutableLiveData("")
  val extractedVideoUrl: LiveData<String> = _extractedVideoUrl

  @SuppressLint("StaticFieldLeak")
  fun extract(context: Context, youtubeLink: String) {

    object : YouTubeExtractor(context) {
      override fun onExtractionComplete(ytFiles: SparseArray<YtFile>?, vMeta: VideoMeta?) {
        if (ytFiles != null) {
          val iTag = 22
          val streamUrl = ytFiles[iTag].url
          _extractedVideoUrl.value = streamUrl
        }
      }
    }.extract(youtubeLink, false, false)
  }
}