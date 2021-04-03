package com.obvious.nasapictures.singlepicture

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.obvious.nasapictures.model.PictureData

class PictureActivityViewModel(
  private val activity: AppCompatActivity,
  val pictureData: PictureData
) : ViewModel() {
}
