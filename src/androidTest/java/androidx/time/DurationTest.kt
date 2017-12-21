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

import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.Duration

class DurationTest {
    @Test fun destructuring() {
        val (seconds, nanos) = Duration.ofSeconds(12, 3456789)
        assertEquals(12L, seconds)
        assertEquals(3456789, nanos)
    }

    @Test fun unaryMinus() {
        val twoSeconds = Duration.ofSeconds(2L)
        assertEquals(Duration.ofSeconds(-2L), -twoSeconds)
    }

    @Test fun multiply() {
        val twoSections = Duration.ofSeconds(2)
        assertEquals(Duration.ofSeconds(4), twoSections * 2)
    }

    @Test fun divide() {
        val fourSeconds = Duration.ofSeconds(4)
        assertEquals(Duration.ofSeconds(2), fourSeconds / 2)
    }

    @Test fun unitNanos() {
        assertEquals(Duration.ofNanos(2), 2.nanos())
        assertEquals(Duration.ofNanos(2), 2L.nanos())
    }

    @Test fun unitMillis() {
        assertEquals(Duration.ofMillis(2), 2.millis())
        assertEquals(Duration.ofMillis(2), 2L.millis())
    }

    @Test fun unitSeconds() {
        assertEquals(Duration.ofSeconds(2), 2.seconds())
        assertEquals(Duration.ofSeconds(2), 2L.seconds())
    }

    @Test fun unitMinutes() {
        assertEquals(Duration.ofMinutes(2), 2.minutes())
        assertEquals(Duration.ofMinutes(2), 2L.minutes())
    }

    @Test fun unitHours() {
        assertEquals(Duration.ofHours(2), 2.hours())
        assertEquals(Duration.ofHours(2), 2L.hours())
    }
}
