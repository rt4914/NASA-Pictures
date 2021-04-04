package com.obvious.nasapictures.model

/** Data class for PictureData to hold json content. */
data class PictureData(
  val copyright: String,
  val date: String,
  val explanation: String,
  val hdurl: String,
  val title: String,
  val media_type: String,
  val url: String
)
