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
import java.time.Period

@Suppress("DEPRECATION")
@SdkSuppress(minSdkVersion = 26)
class PeriodTest {
    @Test fun destructuring() {
        val (years, months, days) = Period.of(10, 3, 22)
        assertEquals(10, years)
        assertEquals(3, months)
        assertEquals(22, days)
    }

    @Test fun unaryMinus() {
        val fourScoreAndSevenYears = Period.ofYears(87)
        val fourScoreAndSevenYearsAgo = -fourScoreAndSevenYears
        assertEquals(Period.ofYears(-87), fourScoreAndSevenYearsAgo)
    }

    @Test fun multiply() {
        val twoYears = Period.ofYears(2)
        assertEquals(Period.ofYears(4), twoYears * 2)
    }

    @Test fun toDays() {
        assertEquals(Period.ofDays(12), 12.days())
    }

    @Test fun toMonths() {
        assertEquals(Period.ofMonths(12), 12.months())
    }

    @Test fun toYears() {
        assertEquals(Period.ofYears(12), 12.years())
    }
}
