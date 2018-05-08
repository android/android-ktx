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

package androidx.core.util

import android.support.test.filters.SdkSuppress
import android.util.Size
import android.util.SizeF
import org.junit.Assert.assertEquals
import org.junit.Test

@SdkSuppress(minSdkVersion = 21)
class SizeTest {
    @Test fun destructuringSize() {
        val (w, h) = Size(320, 240)
        assertEquals(320, w)
        assertEquals(240, h)
    }

    @Test fun destructuringSizeF() {
        val (w, h) = SizeF(1920.0f, 1080.0f)
        assertEquals(1920.0f, w)
        assertEquals(1080.0f, h)
    }
}
