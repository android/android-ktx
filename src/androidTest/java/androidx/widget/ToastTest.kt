/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.widget

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.RootMatchers.withDecorView
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.widget.Toast
import androidx.kotlin.R
import androidx.kotlin.TestActivity
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ToastTest {
    private lateinit var toast: Toast

    @JvmField @Rule val rule = ActivityTestRule(TestActivity::class.java)

    @After fun cancelToast() {
        toast.cancel()
    }

    @Test fun createToastWithTextShort() {
        rule.runOnUiThread { toast = rule.activity.toast("Short Toast") }
        assertEquals(Toast.LENGTH_SHORT, toast.duration)
        onView(withText("Short Toast")).checkToastDisplayed()
    }

    @Test fun createToastWithTextLong() {
        rule.runOnUiThread { toast = rule.activity.toast("Long Toast", Toast.LENGTH_LONG) }
        assertEquals(Toast.LENGTH_LONG, toast.duration)
        onView(withText("Long Toast")).checkToastDisplayed()
    }

    @Test fun createToastWithResIdShort() {
        rule.runOnUiThread { toast = rule.activity.toast(R.id.text) }
        assertEquals(Toast.LENGTH_SHORT, toast.duration)
        onView(withText(R.id.text)).checkToastDisplayed()
    }

    @Test fun createToastWithResIdLong() {
        rule.runOnUiThread { toast = rule.activity.toast(R.id.text, Toast.LENGTH_LONG) }
        assertEquals(Toast.LENGTH_LONG, toast.duration)
        onView(withText(R.id.text)).checkToastDisplayed()
    }

    private fun ViewInteraction.checkToastDisplayed() {
        inRoot(withDecorView(not(rule.activity.window.decorView))).check(matches(isDisplayed()))
    }
}
