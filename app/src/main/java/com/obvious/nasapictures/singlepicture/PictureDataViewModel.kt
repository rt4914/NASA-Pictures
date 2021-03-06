package com.obvious.nasapictures.singlepicture

import android.text.TextUtils
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModel
import com.obvious.nasapictures.model.PictureData

/** ViewModel for [picture_full_screen_item.xml] in [PictureActivity].  */
class PictureDataViewModel(
  val pictureData: PictureData
) : ViewModel() {

  fun onExplanationTextClick(view: View) {
    val textView = view as TextView
    val isTextViewCollapsed = textView.ellipsize == TextUtils.TruncateAt.END
    if (isTextViewCollapsed) {
      textView.maxLines = Integer.MAX_VALUE
      textView.ellipsize = null
    } else {
      textView.maxLines = 3
      textView.ellipsize = TextUtils.TruncateAt.END
    }
  }
}
