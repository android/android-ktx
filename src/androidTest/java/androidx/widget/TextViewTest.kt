/*
 * Copyright (C) 2018 The Android Open Source Project
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

package androidx.widget

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.test.InstrumentationRegistry
import android.widget.TextView
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test

class TextViewTest {
    private val textView = TextView(InstrumentationRegistry.getContext())

    @Test
    fun updateCompoundDrawables() {
        val drawable = ColorDrawable(Color.RED)
        textView.updateCompoundDrawables(start = drawable, end = drawable)

        assertNotNull(textView.compoundDrawables[0])
        assertNull(textView.compoundDrawables[1])
        assertNotNull(textView.compoundDrawables[2])
        assertNull(textView.compoundDrawables[3])
    }

    @Test
    fun updateCompoundDrawablesWithIntrinsicBounds() {
        val drawable = ColorDrawable(Color.RED)
        textView.updateCompoundDrawablesWithIntrinsicBounds(top = drawable, bottom = drawable)

        assertNull(textView.compoundDrawables[0])
        assertNotNull(textView.compoundDrawables[1])
        assertNull(textView.compoundDrawables[2])
        assertNotNull(textView.compoundDrawables[3])
    }

    @Test
    fun updateCompoundDrawablesWithIntrinsicBoundsWithResourceIds() {
        textView.updateCompoundDrawablesWithIntrinsicBounds(
                start = android.R.drawable.btn_default, bottom = android.R.drawable.btn_default)

        assertNotNull(textView.compoundDrawables[0])
        assertNull(textView.compoundDrawables[1])
        assertNull(textView.compoundDrawables[2])
        assertNotNull(textView.compoundDrawables[3])
    }

    @Test
    fun updateCompoundDrawablesRelative() {
        val drawable = ColorDrawable(Color.RED)
        textView.updateCompoundDrawablesRelative(
                start = drawable, bottom = drawable, end = drawable)

        assertNotNull(textView.compoundDrawablesRelative[0])
        assertNull(textView.compoundDrawablesRelative[1])
        assertNotNull(textView.compoundDrawablesRelative[2])
        assertNotNull(textView.compoundDrawablesRelative[3])
    }

    @Test
    fun updateCompoundDrawablesRelativeWithIntrinsicBounds() {
        textView.updateCompoundDrawablesRelativeWithIntrinsicBounds(top = ColorDrawable(Color.RED))

        assertNull(textView.compoundDrawablesRelative[0])
        assertNotNull(textView.compoundDrawablesRelative[1])
        assertNull(textView.compoundDrawablesRelative[2])
        assertNull(textView.compoundDrawablesRelative[3])
    }

    @Test
    fun updateCompoundDrawablesRelativeWithIntrinsicBoundsWithResourceIds() {
        textView.updateCompoundDrawablesRelativeWithIntrinsicBounds(
                start = android.R.drawable.btn_default,
                top = android.R.drawable.btn_default,
                bottom = android.R.drawable.btn_default,
                end = android.R.drawable.btn_default)

        assertNotNull(textView.compoundDrawablesRelative[0])
        assertNotNull(textView.compoundDrawablesRelative[1])
        assertNotNull(textView.compoundDrawablesRelative[2])
        assertNotNull(textView.compoundDrawablesRelative[3])
    }
}