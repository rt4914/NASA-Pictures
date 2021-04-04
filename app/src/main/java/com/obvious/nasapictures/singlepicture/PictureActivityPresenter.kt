package com.obvious.nasapictures.singlepicture

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.obvious.nasapictures.R
import com.obvious.nasapictures.databinding.PictureActivityBinding
import com.obvious.nasapictures.home.HomeActivity
import com.obvious.nasapictures.model.PictureData
import com.obvious.nasapictures.utility.JsonDataRetriever

/** Presenter for [PictureActivity].  */
class PictureActivityPresenter : AppCompatActivity() {

  lateinit var pictureActivityViewModel: PictureActivityViewModel
  lateinit var pictureDataList: List<PictureData>

  fun handleOnCreate(activity: AppCompatActivity, index: Int) {
    val binding: PictureActivityBinding =
      DataBindingUtil.setContentView(activity, R.layout.picture_activity)
    pictureDataList = JsonDataRetriever(activity).pictureDataList

    pictureActivityViewModel = PictureActivityViewModel(activity, pictureDataList.size)
    pictureActivityViewModel.currentIndex.set(index + 1)

    binding.viewModel = pictureActivityViewModel

    binding.imageSliderViewPager.adapter =
      PictureSliderAdapter(createViewModelItemList(activity))
    binding.imageSliderViewPager.setCurrentItem(index, /* smoothScroll= */ false)

    binding.imageSliderViewPager.registerOnPageChangeCallback(object :
      ViewPager2.OnPageChangeCallback() {
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
    pictureDataList.forEach { pictureData ->
      pictureDataViewModelList.add(PictureDataViewModel(pictureData))
    }
    return pictureDataViewModelList
  }
}
