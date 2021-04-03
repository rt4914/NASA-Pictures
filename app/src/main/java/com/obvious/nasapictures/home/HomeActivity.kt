package com.obvious.nasapictures.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

  lateinit var homeActivityPresenter: HomeActivityPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    homeActivityPresenter = HomeActivityPresenter()
    homeActivityPresenter.handleOnCreate(this)
  }
}
