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

package androidx.core.util

import android.support.test.filters.SdkSuppress
import android.util.Half
import org.junit.Assert.assertEquals
import org.junit.Test

@SdkSuppress(minSdkVersion = 26)
class HalfTest {
    @Test fun shortToHalf() = assertEquals(Half(1.toShort()), 1.toShort().toHalf())

    @Test fun floatToHalf() = assertEquals(Half(1f), 1f.toHalf())

    @Test fun doubleToHalf() = assertEquals(Half(1.0), 1.0.toHalf())

    @Test fun stringToHalf() = assertEquals(Half("1"), "1".toHalf())
}
