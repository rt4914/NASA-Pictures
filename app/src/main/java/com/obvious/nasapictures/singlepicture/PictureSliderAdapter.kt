package com.obvious.nasapictures.singlepicture

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.obvious.nasapictures.databinding.PictureFullScreenItemBinding

/** Adapter to slide between content in ViewPager. */
class PictureSliderAdapter(private val pictureDataList: List<PictureDataViewModel>) :
  RecyclerView.Adapter<PictureSliderAdapter.PagerViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    val binding =
      PictureFullScreenItemBinding.inflate(
        inflater,
        parent,
        /* attachToRoot= */ false
      )
    return PagerViewHolder(binding)
  }

  override fun getItemCount(): Int {
    return pictureDataList.size
  }

  override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
    holder.bind(pictureDataList[position])
  }

  inner class PagerViewHolder(private val binding: PictureFullScreenItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    internal fun bind(pictureDataViewModel: PictureDataViewModel) {
      binding.viewModel = pictureDataViewModel
    }
  }
}
