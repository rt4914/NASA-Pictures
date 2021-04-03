package com.obvious.nasapictures.singlepicture

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.obvious.nasapictures.R
import com.obvious.nasapictures.databinding.PictureActivityBinding
import com.obvious.nasapictures.model.PictureData
import com.obvious.nasapictures.utility.JsonDataRetriever

class PictureActivityPresenter : AppCompatActivity() {

  lateinit var pictureActivityViewModel: PictureActivityViewModel
  lateinit var pictureDataList: List<PictureData>

  fun handleOnCreate(activity: AppCompatActivity, index: Int) {
    val binding: PictureActivityBinding =
      DataBindingUtil.setContentView(activity, R.layout.picture_activity)
    pictureDataList = JsonDataRetriever(activity).pictureDataList

    pictureActivityViewModel = PictureActivityViewModel(activity, pictureDataList[index])

    binding.imageSliderViewPager.adapter = PictureSliderAdapter(activity, pictureDataList)
    binding.imageSliderViewPager.setCurrentItem(index, /* smoothScroll= */ true)
  }
}
