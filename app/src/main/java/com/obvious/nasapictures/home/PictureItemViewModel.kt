package com.obvious.nasapictures.home

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import com.obvious.nasapictures.model.PictureData

class PictureItemViewModel(
  private val activity: AppCompatActivity,
  val pictureData: PictureData,
  private val index: Int
) : ViewModel() {

  fun onItemClick() {
    (activity as PictureItemClickListener).onPictureClicked(index)
  }
}
