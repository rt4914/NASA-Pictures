package com.obvious.nasapictures.singlepicture

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
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

    pictureActivityViewModel = PictureActivityViewModel(activity, pictureDataList.size)
    pictureActivityViewModel.currentIndex.set(index)

    binding.viewModel = pictureActivityViewModel

    binding.imageSliderViewPager.adapter =
      PictureSliderAdapter(activity, createViewModelItemList(activity))
    binding.imageSliderViewPager.setCurrentItem(index, /* smoothScroll= */ true)

    binding.imageSliderViewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

      override fun onPageScrollStateChanged(state: Int) {
      }

      override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
      }

      override fun onPageSelected(position: Int) {
        pictureActivityViewModel.currentIndex.set(position + 1)
      }
    })
  }

  private fun createViewModelItemList(activity: AppCompatActivity): ArrayList<PictureDataViewModel> {
    val pictureDataViewModelList = ArrayList<PictureDataViewModel>()
    pictureDataList.forEachIndexed { index, pictureData ->
      pictureDataViewModelList.add(
        PictureDataViewModel(
          pictureData,
          index
        )
      )
    }
    return pictureDataViewModelList
  }
}
