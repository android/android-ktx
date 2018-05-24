/*
 * Copyright (C) 2017 The Android Open Source Project
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

package androidx.core.widget

import android.support.test.InstrumentationRegistry
import android.support.test.filters.SdkSuppress
import android.support.test.rule.ActivityTestRule
import android.widget.ImageView
import androidx.core.kotlin.test.R
import androidx.core.TestActivity
import org.junit.Rule
import org.junit.Test

@SdkSuppress(minSdkVersion = 16)
class ImageViewTest {
    @JvmField @Rule val rule = ActivityTestRule<TestActivity>(TestActivity::class.java)

    @Test fun setFilter() {
        rule.runOnUiThread {
            val view = rule.activity.findViewById<ImageView>(R.id.image_view)
            view.filter(0f)
        }
        InstrumentationRegistry.getInstrumentation().waitForIdleSync()
    }
}
