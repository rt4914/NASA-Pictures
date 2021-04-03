package com.obvious.nasapictures

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.obvious.nasapictures.model.PictureData
import com.obvious.nasapictures.utility.JsonDataRetriever
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/** Tests for [JsonDataRetrieverTest]. */
@RunWith(AndroidJUnit4::class)
@Config(sdk = [28])
class JsonDataRetrieverTest {

  lateinit var pictureDataList: List<PictureData>

  @Before
  fun setup() {
    pictureDataList = JsonDataRetriever(ApplicationProvider.getApplicationContext()).pictureDataList
  }

  @Test
  fun testJsonDataRetriever_readData_sizeIsCorrect() {
    assertThat(pictureDataList.size).isEqualTo(26)
  }

  @Test
  fun testJsonDataRetriever_readData_parsingIsCorrect() {
    val pictureData = pictureDataList[0]

    assertThat(pictureData.copyright).isEqualTo("ESA/HubbleNASA")
    assertThat(pictureData.date).isEqualTo("2019-12-01")
    assertThat(pictureData.explanation).contains("Why does this galaxy have a ring of bright blue stars?")
    assertThat(pictureData.hdurl).isEqualTo("https://apod.nasa.gov/apod/image/1912/M94_Hubble_1002.jpg")
    assertThat(pictureData.media_type).isEqualTo("image")
    assertThat(pictureData.title).isEqualTo("Starburst Galaxy M94 from Hubble")
    assertThat(pictureData.url).isEqualTo("https://apod.nasa.gov/apod/image/1912/M94_Hubble_960.jpg")
  }
}
