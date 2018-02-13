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

package androidx.content

import android.graphics.Rect
import android.support.test.rule.ActivityTestRule
import androidx.kotlin.TestActivityWithFragment
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class FragmentTest {

    @JvmField
    @Rule
    val rule = ActivityTestRule<TestActivityWithFragment>(TestActivityWithFragment::class.java)

    @Test
    fun testScreenRect() {
        val fragment = rule.activity.fragmentManager.fragments[0]
        val displayRectangle = Rect()
        rule.activity.window.decorView.getWindowVisibleDisplayFrame(displayRectangle)
        assertEquals(fragment.screenRect, displayRectangle)
    }

    @Test
    fun testWithActivity() {
        val fragment = rule.activity.fragmentManager.fragments[0]
        var localSuccess = false
        fragment.withActivity<TestActivityWithFragment> {
            localSuccess = success
        }
        assertEquals(localSuccess, true)
    }

    @Test
    fun testWithActivityReturns() {
        val fragment = rule.activity.fragmentManager.fragments[0]
        val localSuccess = fragment.withActivity<TestActivityWithFragment, Boolean> {
            success
        }
        assertEquals(localSuccess, true)
    }

}