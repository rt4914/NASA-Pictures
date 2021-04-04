package com.obvious.nasapictures.home

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.obvious.nasapictures.R
import com.obvious.nasapictures.databinding.HomeActivityBinding
import com.obvious.nasapictures.model.PictureData
import com.obvious.nasapictures.utility.AutoFitGridLayoutManager
import com.obvious.nasapictures.utility.JsonDataRetriever

/** Presenter for [HomeActivity].  */
class HomeActivityPresenter {
  lateinit var pictureDataList: List<PictureData>

  fun handleOnCreate(activity: AppCompatActivity) {
    val binding: HomeActivityBinding = DataBindingUtil.setContentView(
      activity,
      R.layout.home_activity
    )

    pictureDataList = JsonDataRetriever(activity).pictureDataList
    val pictureAdapter = PictureAdapter(createViewModelItemList(activity))

    val layoutManager = AutoFitGridLayoutManager(
      activity,
      activity.resources.getDimensionPixelSize(R.dimen.home_item_width)
    )

    binding.pictureRecyclerView.apply {
      this.layoutManager = layoutManager
      this.adapter = pictureAdapter
    }
  }

  private fun createViewModelItemList(activity: AppCompatActivity): ArrayList<PictureItemViewModel> {
    val pictureItemViewModelList = ArrayList<PictureItemViewModel>()
    pictureDataList.forEachIndexed { index, pictureData ->
      pictureItemViewModelList.add(
        PictureItemViewModel(
          activity,
          pictureData,
          index
        )
      )
    }
    return pictureItemViewModelList
  }
}
