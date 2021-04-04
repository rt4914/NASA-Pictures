package com.obvious.nasapictures

import android.app.Activity
import android.content.Intent
import android.text.Layout
import android.view.View
import android.widget.TextView
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.ActivityResultMatchers.hasResultCode
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.google.common.truth.Truth.assertThat
import com.obvious.nasapictures.OrientationChangeAction.Companion.orientationLandscape
import com.obvious.nasapictures.singlepicture.PictureActivity
import org.hamcrest.CoreMatchers.*
import org.hamcrest.TypeSafeMatcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

private const val TITLE_0 = "Starburst Galaxy M94 from Hubble"
private const val EXPLANATION_0 =
  "Why does this galaxy have a ring of bright blue stars?  Beautiful island universe Messier 94 lies a mere 15 million light-years distant in the northern constellation of the Hunting Dogs (Canes Venatici). A popular target for Earth-based astronomers, the face-on spiral galaxy is about 30,000 light-years across, with spiral arms sweeping through the outskirts of its broad disk. But this Hubble Space Telescope field of view spans about 7,000 light-years across M94's central region. The featured close-up highlights the galaxy's compact, bright nucleus, prominent inner dust lanes, and the remarkable bluish ring of young massive stars. The ring stars are all likely less than 10 million years old, indicating that M94 is a starburst galaxy that is experiencing an epoch of rapid star formation from inspiraling gas. The circular ripple of blue stars is likely a wave propagating outward, having been triggered by the gravity and rotation of a oval matter distributions. Because M94 is relatively nearby, astronomers can better explore details of its starburst ring.    Astrophysicists: Browse 2,000+ codes in the Astrophysics Source Code Library"

private const val TITLE_1 = "M27: The Dumbbell Nebula"
private const val EXPLANATION_1 =
  "Is this what will become of our Sun? Quite possibly.  The first hint of our Sun's future was discovered inadvertently in 1764. At that time, Charles Messier was compiling a list of diffuse objects not to be confused with comets. The 27th object on Messier's list, now known as M27 or the Dumbbell Nebula, is a planetary nebula, the type of nebula our Sun will produce when nuclear fusion stops in its core. M27 is one of the brightest planetary nebulae on the sky, and can be seen toward the constellation of the Fox (Vulpecula) with binoculars. It takes light about 1000 years to reach us from M27, featured here in colors emitted by hydrogen and oxygen. Understanding the physics and significance of M27 was well beyond 18th century science. Even today, many things remain mysterious about bipolar planetary nebula like M27, including the physical mechanism that expels a low-mass star's gaseous outer-envelope, leaving an X-ray hot white dwarf.   APOD across world languages: Arabic, Catalan, Chinese (Beijing), Chinese (Taiwan), Croatian, Czech, Dutch, Farsi, French, French, German, Hebrew, Indonesian, Japanese, Korean, Montenegrin, Polish, Russian, Serbian, Slovenian,  Spanish and Ukrainian"

/** Test for [PictureActivity]. */
@RunWith(AndroidJUnit4::class)
class PictureActivityTest {

  @get:Rule
  val activityTestRule = ActivityTestRule(
    PictureActivity::class.java,
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
  fun testPictureActivity_labelIsCorrect() {
    activityTestRule.launchActivity(createPictureActivityIntent(index = 0))
    val title = activityTestRule.activity.title
    assertThat(title).isEqualTo(context.resources.getString(R.string.picture_activity_title))
  }

  @Test
  fun testPictureActivity_clickOnCloseIcon_isFinishingActivity() {
    activityTestRule.launchActivity(createPictureActivityIntent(index = 0))
    onView(withId(R.id.close_icon_image_view)).perform(click())
    assertThat(activityTestRule.activityResult, hasResultCode(Activity.RESULT_CANCELED))
  }

  @Test
  fun testPictureActivity_closeIcon_contentDescriptionIsCorrect() {
    launch<PictureActivity>(createPictureActivityIntent(index = 0)).use {
      onView(withId(R.id.close_icon_image_view)).check(
        matches(
          withContentDescription(containsString(context.resources.getString(R.string.close)))
        )
      )
    }
  }

  @Test
  fun testPictureActivity_currentIndexText_checkFirst_isCorrect() {
    launch<PictureActivity>(createPictureActivityIntent(index = 0)).use {
      onView(withId(R.id.current_index_text_view)).check(matches(withText("1/26")))
    }
  }

  @Test
  fun testPictureActivity_currentIndexText_checkInBetween_isCorrect() {
    launch<PictureActivity>(createPictureActivityIntent(index = 8)).use {
      onView(withId(R.id.current_index_text_view)).check(matches(withText("9/26")))
    }
  }

  @Test
  fun testPictureActivity_currentIndexText_checkLast_isCorrect() {
    launch<PictureActivity>(createPictureActivityIntent(index = 25)).use {
      onView(withId(R.id.current_index_text_view)).check(matches(withText("26/26")))
    }
  }

  @Test
  fun testPictureActivity_titleText_isCorrect() {
    launch<PictureActivity>(createPictureActivityIntent(index = 0)).use {
      onView(allOf(withId(R.id.title_text_view), isDisplayed())).check(
        matches(withText(TITLE_0))
      )
    }
  }

  @Test
  fun testPictureActivity_titleText_configurationChange_isCorrect() {
    launch<PictureActivity>(createPictureActivityIntent(index = 1)).use {
      orientationLandscape()
      onView(allOf(withId(R.id.title_text_view), isDisplayed())).check(
        matches(withText(TITLE_1))
      )
    }
  }

  @Test
  fun testPictureActivity_explanationText_isCorrect() {
    launch<PictureActivity>(createPictureActivityIntent(index = 0)).use {
      onView(allOf(withId(R.id.explanation_text_view), isDisplayed())).check(
        matches(withSubstring("Why does this galaxy have a ring of bright blue stars?"))
      )
    }
  }

  @Test
  fun testPictureActivity_explanationText_defaultMaxLines_isThree() {
    launch<PictureActivity>(createPictureActivityIntent(index = 0)).use {
      onView(allOf(withId(R.id.explanation_text_view), isDisplayed())).check(
        matches(isTextInLines(3))
      )
    }
  }

  @Test
  fun testPictureActivity_explanationText_configurationChange_defaultMaxLines_isThree() {
    launch<PictureActivity>(createPictureActivityIntent(index = 0)).use {
      orientationLandscape()
      onView(allOf(withId(R.id.explanation_text_view), isDisplayed())).check(
        matches(isTextInLines(3))
      )
    }
  }

  @Test
  fun testPictureActivity_clickExplanationText_textIsFullyVisible() {
    launch<PictureActivity>(createPictureActivityIntent(index = 0)).use {
      onView(allOf(withId(R.id.explanation_text_view), isDisplayed())).perform(click())
      onView(allOf(withId(R.id.explanation_text_view), isDisplayed())).check(
        matches(notEllipsized())
      )
    }
  }

  @Test
  fun testPictureActivity_clickExplanationText_configurationChange_textIsFullyVisible() {
    launch<PictureActivity>(createPictureActivityIntent(index = 0)).use {
      onView(allOf(withId(R.id.explanation_text_view), isDisplayed())).perform(click())
      orientationLandscape()
      onView(allOf(withId(R.id.explanation_text_view), isDisplayed())).check(
        matches(notEllipsized())
      )
    }
  }

  @Test
  fun testPictureActivity_copyrightText_isCorrect() {
    launch<PictureActivity>(createPictureActivityIntent(index = 0)).use {
      onView(allOf(withId(R.id.copyright_name_text_view), isDisplayed())).check(
        matches(withSubstring("ESA/HubbleNASA"))
      )
    }
  }

  @Test
  fun testPictureActivity_dateText_isCorrect() {
    launch<PictureActivity>(createPictureActivityIntent(index = 0)).use {
      onView(allOf(withId(R.id.date_text_view), isDisplayed())).check(
        matches(withSubstring("2019-12-01"))
      )
    }
  }

  @Test
  fun testPictureActivity_swipeRight_titleText_isCorrect() {
    launch<PictureActivity>(createPictureActivityIntent(index = 1)).use {
      onView(withId(R.id.image_slider_view_pager)).perform(swipeRight())
      // Note to Reviewer: This is not the best way to write this test case but because it was
      // failing because of effective visibility I had to use this function for now.
      onView(allOf(withId(R.id.title_text_view), isDisplayingAtLeast(80))).check(
        matches(withText(TITLE_0))
      )
    }
  }

  @Test
  fun testPictureActivity_swipeLeft_titleText_isCorrect() {
    launch<PictureActivity>(createPictureActivityIntent(index = 0)).use {
      onView(withId(R.id.image_slider_view_pager)).perform(swipeLeft())
      // Note to Reviewer: This is not the best way to write this test case but because it was
      // failing because of effective visibility I had to use this function for now.
      onView(allOf(withId(R.id.title_text_view), isDisplayingAtLeast(80))).check(
        matches(withText(TITLE_1))
      )
    }
  }

  private fun createPictureActivityIntent(index: Int): Intent {
    return PictureActivity.createIntent(context, index)
  }

  // Reference: https://stackoverflow.com/a/46296194/5860956
  private fun isTextInLines(lines: Int): TypeSafeMatcher<View?>? {
    return object : TypeSafeMatcher<View?>() {

      override fun matchesSafely(item: View?): Boolean {
        return (item as TextView).lineCount == lines
      }

      override fun describeTo(description: org.hamcrest.Description?) {
        description?.appendText("isTextInLines")
      }
    }
  }

  private fun notEllipsized() = object : TypeSafeMatcher<View>() {

    override fun matchesSafely(v: View): Boolean {
      if (v !is TextView) {
        return false
      }
      val textView: TextView = v
      val layout: Layout = textView.layout
      val lines = layout.lineCount
      if (lines > 0) {
        val ellipsisCount = layout.getEllipsisCount(lines - 1)
        if (ellipsisCount <= 0) {
          return true
        }
      }

      return false
    }

    override fun describeTo(description: org.hamcrest.Description?) {
      description?.appendText("with ellipsized text")
    }
  }
}
