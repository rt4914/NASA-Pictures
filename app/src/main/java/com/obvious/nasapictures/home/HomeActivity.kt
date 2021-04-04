package com.obvious.nasapictures.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.obvious.nasapictures.singlepicture.PictureActivity

/** Activity for displaying picture list in home screen.  */
class HomeActivity : AppCompatActivity(), PictureItemClickListener {

  lateinit var homeActivityPresenter: HomeActivityPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    homeActivityPresenter = HomeActivityPresenter()
    homeActivityPresenter.handleOnCreate(this)
  }

  override fun onPictureClicked(position: Int) {
    startActivity(PictureActivity.createIntent(this, position))
  }
}
