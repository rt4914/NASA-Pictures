package com.obvious.nasapictures.singlepicture

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.obvious.nasapictures.model.PictureData

/** ViewModel for [picture_activity.xml].  */
class PictureActivityViewModel(
  private val activity: AppCompatActivity,
  val totalPictures: Int
) : ViewModel() {

  val currentIndex = ObservableField(0)

  fun onCloseIconClick() {
    (activity as CloseIconClickListener).onCloseIconClicked()
  }
}
