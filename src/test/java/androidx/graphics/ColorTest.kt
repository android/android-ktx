/*
 * Copyright (C) 2017 The Android Open Source Project
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

package androidx.graphics

import android.graphics.Color
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE, sdk = [26])
class ColorTest {
    @Test fun toColor() = assertEquals(Color.valueOf(0x337f3010), 0x337f3010.toColor())
    @Test fun destructuring() {
        val (r, g, b, a) = 0x337f3010.toColor()
        assertEquals(0.5f, r, 1e-2f)
        assertEquals(0.19f, g, 1e-2f)
        assertEquals(0.06f, b, 1e-2f)
        assertEquals(0.2f, a, 1e-2f)
    }

    @Test fun alpha() = assertEquals(0x33, 0x337f3010.alpha)
    @Test fun red() = assertEquals(0x7f, 0x337f3010.red)
    @Test fun green() = assertEquals(0x30, 0x337f3010.green)
    @Test fun blue() = assertEquals(0x10, 0x337f3010.blue)
    @Test fun luminance() = assertEquals(0.212f, 0xff7f7f7f.toInt().luminance(), 1e-3f)
}
