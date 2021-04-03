package com.obvious.nasapictures.singlepicture

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import com.obvious.nasapictures.model.PictureData
import com.obvious.nasapictures.databinding.PictureFullScreenItemBinding

class PictureSliderAdapter(
  activity: AppCompatActivity,
  private val pictureDataList: List<PictureDataViewModel>
) : PagerAdapter() {
  private val inflater: LayoutInflater = LayoutInflater.from(activity)
  override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
    container.removeView(`object` as View)
  }

  override fun getCount(): Int {
    return pictureDataList.size
  }

  override fun instantiateItem(view: ViewGroup, position: Int): Any {
    val binding =
      PictureFullScreenItemBinding.inflate(
        inflater,
        view,
        /* attachToRoot= */ false
      )
    binding.viewModel = pictureDataList[position]
    view.addView(binding.root)
    return binding.root
  }

  override fun isViewFromObject(
    view: View,
    `object`: Any
  ): Boolean {
    return view == `object`
  }
}
