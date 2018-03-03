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
import java.time.Month
import java.time.YearMonth

@Suppress("DEPRECATION")
@SdkSuppress(minSdkVersion = 26)
class YearMonthTest {
    @Test fun destructuring() {
        val (year, month) = YearMonth.of(2017, 12)
        assertEquals(2017, year)
        assertEquals(Month.DECEMBER, month)
    }
}
