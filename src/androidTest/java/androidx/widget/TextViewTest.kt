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
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertNotNull
import org.junit.Test

class TextViewTest {
    private val textView = TextView(InstrumentationRegistry.getContext())

    @Test
    fun updateCompoundDrawables() {
        val drawable = ColorDrawable(Color.RED)
        textView.updateCompoundDrawables(left = drawable, right = drawable)

        assertEquals(drawable, textView.compoundDrawables[0])
        assertEquals(drawable, textView.compoundDrawables[2])
        assertNull(textView.compoundDrawables[1])
        assertNull(textView.compoundDrawables[3])
    }

    @Test
    fun updateCompoundDrawables_keepsDrawables() {
        val drawableRed = ColorDrawable(Color.RED)
        val drawableBlack = ColorDrawable(Color.BLACK)
        textView.updateCompoundDrawables(left = drawableRed, top = drawableBlack)
        textView.updateCompoundDrawables(right = drawableBlack, top = null)
        assertEquals(drawableRed, textView.compoundDrawables[0])
        assertEquals(drawableBlack, textView.compoundDrawables[2])
        assertNull(textView.compoundDrawables[1])
        assertNull(textView.compoundDrawables[3])
    }

    @Test
    fun updateCompoundDrawablesWithIntrinsicBounds() {
        val drawable = ColorDrawable(Color.RED)
        textView.updateCompoundDrawablesWithIntrinsicBounds(top = drawable, bottom = drawable)

        assertEquals(drawable, textView.compoundDrawables[1])
        assertEquals(drawable, textView.compoundDrawables[3])
        assertNull(textView.compoundDrawables[0])
        assertNull(textView.compoundDrawables[2])
    }

    @Test
    fun updateCompoundDrawablesWithIntrinsicBounds_keepsDrawables() {
        val drawableRed = ColorDrawable(Color.RED)
        val drawableBlack = ColorDrawable(Color.BLACK)
        textView.updateCompoundDrawablesWithIntrinsicBounds(
            top = drawableRed, bottom = drawableBlack)
        textView.updateCompoundDrawablesWithIntrinsicBounds(right = drawableBlack, bottom = null)

        assertEquals(drawableRed, textView.compoundDrawables[1])
        assertEquals(drawableBlack, textView.compoundDrawables[2])
        assertNull(textView.compoundDrawables[0])
        assertNull(textView.compoundDrawables[3])
    }

    @Test
    fun updateCompoundDrawablesWithIntrinsicBoundsWithResourceIds() {
        textView.updateCompoundDrawablesWithIntrinsicBounds(
                left = android.R.drawable.btn_default, bottom = android.R.drawable.btn_default)

        assertNotNull(textView.compoundDrawables[0])
        assertNotNull(textView.compoundDrawables[3])
        assertNull(textView.compoundDrawables[1])
        assertNull(textView.compoundDrawables[2])
    }

    @Test
    fun updateCompoundDrawablesWithIntrinsicBoundsWithResourceIds_keepsDrawables() {
        textView.updateCompoundDrawablesWithIntrinsicBounds(
                left = android.R.drawable.btn_default, bottom = android.R.drawable.btn_default)
        textView.updateCompoundDrawablesWithIntrinsicBounds(
                left = 0, right = android.R.drawable.btn_default)

        assertNotNull(textView.compoundDrawables[3])
        assertNotNull(textView.compoundDrawables[2])
        assertNull(textView.compoundDrawables[0])
        assertNull(textView.compoundDrawables[1])
    }

    @Test
    fun updateCompoundDrawablesRelative() {
        val drawableRed = ColorDrawable(Color.RED)
        textView.updateCompoundDrawablesRelative(
                start = drawableRed, bottom = drawableRed, end = drawableRed)

        assertEquals(drawableRed, textView.compoundDrawablesRelative[0])
        assertEquals(drawableRed, textView.compoundDrawablesRelative[2])
        assertEquals(drawableRed, textView.compoundDrawablesRelative[3])
        assertNull(textView.compoundDrawablesRelative[1])
    }

    @Test
    fun updateCompoundDrawablesRelative_keepsDrawables() {
        val drawableRed = ColorDrawable(Color.RED)
        val drawableBlack = ColorDrawable(Color.BLACK)
        textView.updateCompoundDrawablesRelative(
                start = drawableRed, bottom = drawableRed, end = drawableRed)
        textView.updateCompoundDrawablesRelative(
                start = drawableBlack, top = drawableRed, end = null)

        assertEquals(drawableBlack, textView.compoundDrawablesRelative[0])
        assertEquals(drawableRed, textView.compoundDrawablesRelative[1])
        assertEquals(drawableRed, textView.compoundDrawablesRelative[3])
        assertNull(textView.compoundDrawablesRelative[2])
    }

    @Test
    fun updateCompoundDrawablesRelativeWithIntrinsicBounds() {
        val drawable = ColorDrawable(Color.RED)
        textView.updateCompoundDrawablesRelativeWithIntrinsicBounds(top = drawable)

        assertEquals(drawable, textView.compoundDrawablesRelative[1])
        assertNull(textView.compoundDrawablesRelative[0])
        assertNull(textView.compoundDrawablesRelative[2])
        assertNull(textView.compoundDrawablesRelative[3])
    }

    @Test
    fun updateCompoundDrawablesRelativeWithIntrinsicBounds_keepsDrawables() {
        val drawableRed = ColorDrawable(Color.RED)
        val drawableBlack = ColorDrawable(Color.BLACK)
        textView.updateCompoundDrawablesRelativeWithIntrinsicBounds(
                top = drawableRed, bottom = drawableRed)
        textView.updateCompoundDrawablesRelativeWithIntrinsicBounds(
                bottom = null, end = drawableBlack)

        assertEquals(drawableRed, textView.compoundDrawablesRelative[1])
        assertEquals(drawableBlack, textView.compoundDrawablesRelative[2])
        assertNull(textView.compoundDrawablesRelative[0])
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

    @Test
    fun updateCompoundDrawablesRelativeWithIntrinsicBoundsWithResourceIds_keepsDrawables() {
        textView.updateCompoundDrawablesRelativeWithIntrinsicBounds(
                start = android.R.drawable.btn_default,
                top = android.R.drawable.btn_default,
                bottom = android.R.drawable.btn_default,
                end = android.R.drawable.btn_default)
        textView.updateCompoundDrawablesRelativeWithIntrinsicBounds(start = 0, end = 0)

        assertNotNull(textView.compoundDrawablesRelative[1])
        assertNotNull(textView.compoundDrawablesRelative[3])
        assertNull(textView.compoundDrawablesRelative[0])
        assertNull(textView.compoundDrawablesRelative[2])
    }
}