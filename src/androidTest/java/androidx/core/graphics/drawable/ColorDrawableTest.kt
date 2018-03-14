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

package androidx.core.graphics.drawable

import android.graphics.Color
import android.support.test.filters.SdkSuppress
import org.junit.Assert.assertEquals
import org.junit.Test

class ColorDrawableTest {
    @Test fun fromInt() {
        val drawable = Color.CYAN.toDrawable()
        assertEquals(Color.CYAN, drawable.color)
    }

    @SdkSuppress(minSdkVersion = 26)
    @Test fun fromColor() {
        val drawable = Color.valueOf(Color.CYAN).toDrawable()
        assertEquals(Color.CYAN, drawable.color)
    }
}
