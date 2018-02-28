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

import android.support.test.InstrumentationRegistry
import androidx.kotlin.test.R
import org.junit.Assert.assertEquals
import org.junit.Test

class DisplayMetricsTest {
    private val res = InstrumentationRegistry.getContext().resources

    @Test
    fun pxToDp() {
        assertEquals(res.toDp(56), res.getDimension(R.dimen.dp))
        assertEquals(res.toDp(56.5f), res.getDimension(R.dimen.decimal_point_dp))
    }

    @Test
    fun pxToSp() {
        assertEquals(res.toSp(32), res.getDimension(R.dimen.sp))
        assertEquals(res.toSp(32.5f), res.getDimension(R.dimen.decimal_point_sp))
    }

    @Test
    fun dpToPx() {
        assertEquals(56f, res.getDp(res.getDimension(R.dimen.dp)))
        assertEquals(56.5f, res.getDp(res.getDimension(R.dimen.decimal_point_dp)))
    }

    @Test
    fun spToPx() {
        assertEquals(32f, res.getSp(res.getDimension(R.dimen.sp)))
        assertEquals(32.5f, res.getSp(res.getDimension(R.dimen.decimal_point_sp)))
    }
}