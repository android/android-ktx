/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.view

import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.support.test.InstrumentationRegistry
import android.widget.TextView
import androidx.graphics.createBitmap
import org.junit.Assert
import org.junit.Test

class TextViewTest {

    private val context = InstrumentationRegistry.getContext()
    private val resources = context.resources
    private val textView = TextView(context)

    @Test
    fun testDrawables() {
        val original = createBitmap(10, 10).apply {
            eraseColor(Color.RED)
        }
        val drawable = BitmapDrawable(resources, original)
        textView.updateCompoundDrawablesRelative(start = drawable)

        Assert.assertEquals(drawable, textView.compoundDrawables[0])
        Assert.assertNull(textView.compoundDrawables[1])
        Assert.assertNull(textView.compoundDrawables[2])
        Assert.assertNull(textView.compoundDrawables[3])
    }

}