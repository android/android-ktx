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
import java.time.DayOfWeek

@Suppress("DEPRECATION")
@SdkSuppress(minSdkVersion = 26)
class DayOfWeekTest {
    @Test fun fromInt() {
        assertEquals(DayOfWeek.MONDAY, 1.asDayOfWeek())
        assertEquals(DayOfWeek.TUESDAY, 2.asDayOfWeek())
        assertEquals(DayOfWeek.WEDNESDAY, 3.asDayOfWeek())
        assertEquals(DayOfWeek.THURSDAY, 4.asDayOfWeek())
        assertEquals(DayOfWeek.FRIDAY, 5.asDayOfWeek())
        assertEquals(DayOfWeek.SATURDAY, 6.asDayOfWeek())
        assertEquals(DayOfWeek.SUNDAY, 7.asDayOfWeek())
    }

    @Test fun toInt() {
        assertEquals(1, DayOfWeek.MONDAY.asInt())
        assertEquals(2, DayOfWeek.TUESDAY.asInt())
        assertEquals(3, DayOfWeek.WEDNESDAY.asInt())
        assertEquals(4, DayOfWeek.THURSDAY.asInt())
        assertEquals(5, DayOfWeek.FRIDAY.asInt())
        assertEquals(6, DayOfWeek.SATURDAY.asInt())
        assertEquals(7, DayOfWeek.SUNDAY.asInt())
    }
}
