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

import android.R
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

    private val resourceId = R.drawable.btn_default
    private val drawable: BitmapDrawable
        get() {
            return BitmapDrawable(resources,
                    createBitmap(10, 10).apply {
                        eraseColor(Color.RED)
                    })
        }

    @Test
    fun updateCompoundDrawables() {
        textView.updateCompoundDrawables(start = drawable, end = drawable)

        Assert.assertNotNull(textView.compoundDrawables[0])
        Assert.assertNull(textView.compoundDrawables[1])
        Assert.assertNotNull(textView.compoundDrawables[2])
        Assert.assertNull(textView.compoundDrawables[3])
    }

    @Test
    fun updateCompoundDrawablesWithIntrinsicBounds() {
        textView.updateCompoundDrawablesWithIntrinsicBounds(top = drawable, bottom = drawable)

        Assert.assertNull(textView.compoundDrawables[0])
        Assert.assertNotNull(textView.compoundDrawables[1])
        Assert.assertNull(textView.compoundDrawables[2])
        Assert.assertNotNull(textView.compoundDrawables[3])
    }

    @Test
    fun updateCompoundDrawablesWithIntrinsicBoundsWithResourceIds() {
        textView.updateCompoundDrawablesWithIntrinsicBounds(start = resourceId, bottom = resourceId)

        Assert.assertNotNull(textView.compoundDrawables[0])
        Assert.assertNull(textView.compoundDrawables[1])
        Assert.assertNull(textView.compoundDrawables[2])
        Assert.assertNotNull(textView.compoundDrawables[3])
    }

    @Test
    fun updateCompoundDrawablesRelative() {
        textView.updateCompoundDrawablesRelative(start = drawable, bottom = drawable, end = drawable)

        Assert.assertNotNull(textView.compoundDrawablesRelative[0])
        Assert.assertNull(textView.compoundDrawablesRelative[1])
        Assert.assertNotNull(textView.compoundDrawablesRelative[2])
        Assert.assertNotNull(textView.compoundDrawablesRelative[3])
    }

    @Test
    fun updateCompoundDrawablesRelativeWithIntrinsicBounds() {
        textView.updateCompoundDrawablesRelativeWithIntrinsicBounds(top = drawable)

        Assert.assertNull(textView.compoundDrawablesRelative[0])
        Assert.assertNotNull(textView.compoundDrawablesRelative[1])
        Assert.assertNull(textView.compoundDrawablesRelative[2])
        Assert.assertNull(textView.compoundDrawablesRelative[3])
    }

    @Test
    fun updateCompoundDrawablesRelativeWithIntrinsicBoundsWithResourceIds() {
        textView.updateCompoundDrawablesRelativeWithIntrinsicBounds(
                start = resourceId, top = resourceId, bottom = resourceId, end = resourceId)

        Assert.assertNotNull(textView.compoundDrawablesRelative[0])
        Assert.assertNotNull(textView.compoundDrawablesRelative[1])
        Assert.assertNotNull(textView.compoundDrawablesRelative[2])
        Assert.assertNotNull(textView.compoundDrawablesRelative[3])
    }
}