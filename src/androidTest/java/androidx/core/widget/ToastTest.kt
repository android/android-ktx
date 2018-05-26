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

package androidx.core.widget

import android.support.test.InstrumentationRegistry
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.ktx.test.R
import androidx.core.view.forEach
import androidx.core.view.isVisible
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ToastTest {
    private val context = InstrumentationRegistry.getContext()
    private val instrumentation = InstrumentationRegistry.getInstrumentation()

    @Test
    fun createToastWithTextShort() = instrumentation.runOnMainSync {
        val toast = context.toast("Short Toast")
        assertEquals(Toast.LENGTH_SHORT, toast.duration)
        assertTrue(containsText(toast.view, "Short Toast"))
        assertTrue(toast.view.isVisible)
    }

    @Test
    fun createToastWithTextLong() = instrumentation.runOnMainSync {
        val toast = context.toast("Long Toast", Toast.LENGTH_LONG)
        assertEquals(Toast.LENGTH_LONG, toast.duration)
        assertTrue(containsText(toast.view, "Long Toast"))
        assertTrue(toast.view.isVisible)
    }

    @Test
    fun createToastWithResIdShort() = instrumentation.runOnMainSync {
        val toast = context.toast(R.string.text)
        assertEquals(Toast.LENGTH_SHORT, toast.duration)
        assertTrue(containsText(toast.view, context.getString(R.string.text)))
        assertTrue(toast.view.isVisible)
    }

    @Test
    fun createToastWithResIdLong() = instrumentation.runOnMainSync {
        val toast = context.toast(R.string.text, Toast.LENGTH_LONG)
        assertEquals(Toast.LENGTH_LONG, toast.duration)
        assertTrue(containsText(toast.view, context.getString(R.string.text)))
        assertTrue(toast.view.isVisible)
    }

    private fun containsText(view: View, text: String): Boolean {
        if (view is TextView && view.text == text) {
            return true
        }
        if (view is ViewGroup) {
            view.forEach {
                if (containsText(it, text)) {
                    return true
                }
            }
        }
        return false
    }
}
