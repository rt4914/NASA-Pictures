package com.obvious.nasapictures.singlepicture

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class PictureActivity : AppCompatActivity(), CloseIconClickListener {
  companion object {
    internal const val PICTURE_INDEX = "PictureActivity.picture_index"

    fun createIntent(context: Context, index: Int): Intent {
      val intent = Intent(context, PictureActivity::class.java)
      intent.putExtra(PICTURE_INDEX, index)
      return intent
    }
  }

  lateinit var pictureActivityPresenter: PictureActivityPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val index = intent.getIntExtra(PICTURE_INDEX, /* defaultValue= */ 0)

    pictureActivityPresenter = PictureActivityPresenter()
    pictureActivityPresenter.handleOnCreate(this, index)
  }

  override fun onCloseIconClicked() {
    this.finish()
  }
}
