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

package androidx.view

import android.support.test.InstrumentationRegistry
import android.view.Gravity.LEFT
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup.LayoutParams
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import android.widget.LinearLayout.VERTICAL
import androidx.core.kotlin.test.R
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.fail
import org.junit.Test

class LayoutInflaterTest {
    private var context = InstrumentationRegistry.getTargetContext()
    private var layoutInflater = LayoutInflater.from(context)

    @Test fun inflateLayout() {
        var view = layoutInflater.inflate<View>(R.layout.test_activity, null, false)
        assertNotNull(view)

        try {
            layoutInflater.inflate<View>(-1, null, false)
            fail("should throw exception")
        } catch (e: Exception) {
        }

        val layout = LinearLayout(context)
        layout.orientation = VERTICAL
        layout.setHorizontalGravity(LEFT)
        layout.layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)
        assertEquals(0, layout.childCount)

        view = layoutInflater.inflate<View>(R.layout.test_activity, layout, false)
        assertNotNull(view)
        assertEquals(0, layout.childCount)

        view = layoutInflater.inflate<View>(R.layout.test_activity, layout, true)
        assertNotNull(view)
        assertEquals(1, layout.childCount)
    }
}
