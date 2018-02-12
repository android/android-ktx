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

import android.support.design.internal.SnackbarContentLayout
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.support.test.InstrumentationRegistry
import androidx.kotlin.R
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class SnackbarTest {

    private val context = InstrumentationRegistry.getContext().apply {
        setTheme(R.style.Theme_AppCompat)
    }

    @Test
    fun snackBar_LengthShort() {
        val view = CoordinatorLayout(context)
        val snackbar = view.snackBar(R.string.abc_action_mode_done)

        assertEquals(snackbar.duration, Snackbar.LENGTH_SHORT)

        val snackbarLayout = snackbar.view as Snackbar.SnackbarLayout
        val snackbarContentLayout = snackbarLayout.getChildAt(0) as SnackbarContentLayout
        assertTrue(snackbarContentLayout.messageView != null)
        assertEquals("Done", snackbarContentLayout.messageView.text)
    }

    @Test
    fun snackBar_LengthLong() {
        val view = CoordinatorLayout(context)
        val snackbar = view.snackBar(R.string.abc_action_mode_done, Snackbar.LENGTH_LONG)

        assertEquals(snackbar.duration, Snackbar.LENGTH_LONG)

        val snackbarLayout = snackbar.view as Snackbar.SnackbarLayout
        val snackbarContentLayout = snackbarLayout.getChildAt(0) as SnackbarContentLayout
        assertTrue(snackbarContentLayout.messageView != null)
        assertEquals("Done", snackbarContentLayout.messageView.text)
    }

}