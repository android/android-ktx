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

package androidx.time

import android.support.test.filters.SdkSuppress
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.Year

@Suppress("DEPRECATION")
@SdkSuppress(minSdkVersion = 26)
class YearTest {
    @Test fun fromInt() {
        assertEquals(Year.of(2017), 2017.asYear())
    }

    @Test fun toInt() {
        assertEquals(2017, Year.of(2017).asInt())
    }
}
