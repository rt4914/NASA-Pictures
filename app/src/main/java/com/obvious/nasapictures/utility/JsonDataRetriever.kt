package com.obvious.nasapictures.utility

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.obvious.nasapictures.model.PictureData
import java.io.IOException

class JsonDataRetriever(private val context: Context) {

  val pictureDataList: List<PictureData>

  init {
    val pictureDataJson = readJsonDataFromAsset()
    val pictureDataTypeToken = object : TypeToken<List<PictureData>>() {}.type
    pictureDataList = Gson().fromJson(pictureDataJson, pictureDataTypeToken)
  }

  private fun readJsonDataFromAsset(): String? {
    val jsonString: String
    try {
      jsonString = context.assets.open("data.json").bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
      ioException.printStackTrace()
      return null
    }
    return jsonString
  }
}
