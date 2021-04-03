package com.obvious.nasapictures

import android.content.Intent
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.google.common.truth.Truth.assertThat
import com.obvious.nasapictures.OrientationChangeAction.Companion.orientationLandscape
import com.obvious.nasapictures.RecyclerViewMatcher.Companion.atPosition
import com.obvious.nasapictures.RecyclerViewMatcher.Companion.atPositionOnView
import com.obvious.nasapictures.RecyclerViewMatcher.Companion.hasItemCount
import com.obvious.nasapictures.home.HomeActivity
import com.obvious.nasapictures.singlepicture.PictureActivity
import org.hamcrest.CoreMatchers.containsString
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/** Test for [HomeActivity]. */
@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

  @get:Rule
  val activityTestRule = ActivityTestRule(
    HomeActivity::class.java,
    /* initialTouchMode= */ true,
    /* launchActivity= */ false
  )

  private val context = InstrumentationRegistry.getInstrumentation().targetContext

  @Before
  fun setUp() {
    Intents.init()
  }

  @After
  fun tearDown() {
    Intents.release()
  }

  @Test
  fun testHomeActivity_labelIsCorrect() {
    activityTestRule.launchActivity(createHomeActivityIntent())
    val title = activityTestRule.activity.title
    assertThat(title).isEqualTo(context.resources.getString(R.string.home_activity_title))
  }

  @Test
  fun testHomeActivity_checkNumberOfRecyclerViewItems_isCorrect() {
    launch<HomeActivity>(createHomeActivityIntent()).use {
      onView(withId(R.id.picture_recycler_view))
        .check(hasItemCount(count = 26))
    }
  }

  @Test
  fun testHomeActivity_item0_contentDescriptionIsCorrect() {
    launch<HomeActivity>(createHomeActivityIntent()).use {
      onView(
        atPositionOnView(
          recyclerViewId = R.id.picture_recycler_view,
          position = 0,
          targetViewId = R.id.picture_item_container
        )
      ).check(
        matches(
          withContentDescription(containsString("Starburst Galaxy M94 from Hubble"))
        )
      )
    }
  }

  @Test
  fun testHomeActivity_configurationChange_item0_contentDescriptionIsCorrect() {
    launch<HomeActivity>(createHomeActivityIntent()).use {
      orientationLandscape()
      onView(
        atPositionOnView(
          recyclerViewId = R.id.picture_recycler_view,
          position = 0,
          targetViewId = R.id.picture_item_container
        )
      ).check(
        matches(
          withContentDescription(containsString("Starburst Galaxy M94 from Hubble"))
        )
      )
    }
  }


  @Test
  fun testHomeActivity_item1Click_opensPictureActivitySuccessfully() {
    launch<HomeActivity>(createHomeActivityIntent()).use {
      onView(
        atPosition(
          recyclerViewId = R.id.picture_recycler_view,
          position = 1
        )
      ).perform(click())
      intended(hasComponent(PictureActivity::class.java.name))
      intended(hasExtra(PictureActivity.PICTURE_INDEX, 1))
    }
  }

  private fun createHomeActivityIntent(): Intent {
    return Intent(context, HomeActivity::class.java)
  }
}
