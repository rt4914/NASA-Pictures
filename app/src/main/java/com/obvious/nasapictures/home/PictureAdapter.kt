package com.obvious.nasapictures.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.obvious.nasapictures.databinding.PictureItemBinding

/** Adapter to display list of pictures in [HomeActivity]. */
class PictureAdapter(
  private val pictureList: MutableList<PictureItemViewModel>
) : RecyclerView.Adapter<PictureAdapter.PictureItemViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureItemViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    val binding =
      PictureItemBinding.inflate(
        inflater,
        parent,
        /* attachToRoot= */ false
      )
    return PictureItemViewHolder(binding)
  }

  override fun getItemCount(): Int {
    return pictureList.size
  }

  override fun onBindViewHolder(holder: PictureItemViewHolder, position: Int) {
    holder.bind(pictureList[position])
  }

  inner class PictureItemViewHolder(
    private val binding: PictureItemBinding
  ) : RecyclerView.ViewHolder(binding.root) {
    internal fun bind(pictureItemViewModel: PictureItemViewModel) {
      binding.viewModel = pictureItemViewModel
    }
  }
}
