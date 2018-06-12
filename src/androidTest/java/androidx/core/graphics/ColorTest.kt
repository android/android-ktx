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

package androidx.core.graphics

import android.graphics.Color
import android.graphics.ColorSpace
import android.support.test.filters.SdkSuppress
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ColorTest {
    @SdkSuppress(minSdkVersion = 26)
    @Test fun destructuringColor() {
        val (r, g, b, a) = 0x337f3010.toColor()
        assertEquals(0.5f, r, 1e-2f)
        assertEquals(0.19f, g, 1e-2f)
        assertEquals(0.06f, b, 1e-2f)
        assertEquals(0.2f, a, 1e-2f)
    }

    @Test fun destructuringInt() {
        val (a, r, g, b) = 0x337f3010
        assertEquals(0x33, a)
        assertEquals(0x7f, r)
        assertEquals(0x30, g)
        assertEquals(0x10, b)
    }

    @SdkSuppress(minSdkVersion = 26)
    @Test fun intToColor() = assertEquals(Color.valueOf(0x337f3010), 0x337f3010.toColor())

    @SdkSuppress(minSdkVersion = 26)
    @Test fun intToColorLong() = assertEquals(Color.pack(0x337f3010), 0x337f3010.toColorLong())

    @Test fun alpha() = assertEquals(0x33, 0x337f3010.alpha)
    @Test fun red() = assertEquals(0x7f, 0x337f3010.red)
    @Test fun green() = assertEquals(0x30, 0x337f3010.green)
    @Test fun blue() = assertEquals(0x10, 0x337f3010.blue)

    @SdkSuppress(minSdkVersion = 26)
    @Test fun luminance() = assertEquals(0.212f, 0xff7f7f7f.toInt().luminance, 1e-3f)

    @SdkSuppress(minSdkVersion = 26)
    @Test fun longToColor() {
        assertEquals(Color.valueOf(0x337f3010), Color.pack(0x337f3010).toColor())
    }

    @SdkSuppress(minSdkVersion = 26)
    @Test fun longToColorInt() = assertEquals(0x337f3010, Color.pack(0x337f3010).toColorInt())

    @SdkSuppress(minSdkVersion = 26)
    @Test fun destructuringLong() {
        val (r, g, b, a) = Color.pack(0x337f3010)
        assertEquals(0.20f, a, 1e-2f)
        assertEquals(0.50f, r, 1e-2f)
        assertEquals(0.19f, g, 1e-2f)
        assertEquals(0.06f, b, 1e-2f)
    }

    @SdkSuppress(minSdkVersion = 26)
    @Test fun alphaLong() = assertEquals(0.20f, Color.pack(0x337f3010).alpha, 1e-2f)

    @SdkSuppress(minSdkVersion = 26)
    @Test fun redLong() = assertEquals(0.50f, Color.pack(0x337f3010).red, 1e-2f)

    @SdkSuppress(minSdkVersion = 26)
    @Test fun greenLong() = assertEquals(0.19f, Color.pack(0x337f3010).green, 1e-2f)

    @SdkSuppress(minSdkVersion = 26)
    @Test fun blueLong() = assertEquals(0.06f, Color.pack(0x337f3010).blue, 1e-2f)

    @SdkSuppress(minSdkVersion = 26)
    @Test fun luminanceLong() {
        assertEquals(0.212f, Color.pack(0xff7f7f7f.toInt()).luminance, 1e-3f)
    }

    @SdkSuppress(minSdkVersion = 26)
    @Test fun isSrgb() {
        assertTrue(0x337f3010.toColorLong().isSrgb)
        val c = Color.pack(1.0f, 0.0f, 0.0f, 1.0f, ColorSpace.get(ColorSpace.Named.BT2020))
        assertFalse(c.isSrgb)
    }

    @SdkSuppress(minSdkVersion = 26)
    @Test fun isWideGamut() {
        assertFalse(0x337f3010.toColorLong().isWideGamut)
        val c = Color.pack(1.0f, 0.0f, 0.0f, 1.0f, ColorSpace.get(ColorSpace.Named.BT2020))
        assertTrue(c.isWideGamut)
    }

    @SdkSuppress(minSdkVersion = 26)
    @Test fun getColorSpace() {
        val sRGB = ColorSpace.get(ColorSpace.Named.SRGB)
        assertEquals(sRGB, 0x337f3010.toColorLong().colorSpace)

        val bt2020 = ColorSpace.get(ColorSpace.Named.BT2020)
        val c = Color.pack(1.0f, 0.0f, 0.0f, 1.0f, bt2020)
        assertEquals(bt2020, c.colorSpace)
    }

    @SdkSuppress(minSdkVersion = 26)
    @Test fun addColorsSameColorSpace() {
        val (r, g, b, a) = 0x7f7f0000.toColor() + 0x7f007f00.toColor()
        assertEquals(0.16f, r, 1e-2f)
        assertEquals(0.33f, g, 1e-2f)
        assertEquals(0.00f, b, 1e-2f)
        assertEquals(0.75f, a, 1e-2f)
    }

    @Test fun stringToColorInt() = assertEquals(Color.GREEN, "#00ff00".toColorInt())
}
