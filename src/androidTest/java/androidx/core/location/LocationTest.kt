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

package androidx.core.location

import android.location.Location
import org.junit.Assert.assertEquals
import org.junit.Test

class LocationTest {
    @Test fun destructuring() {
        val (lat, lon) = Location("").apply {
            latitude = 1.0
            longitude = 2.0
        }
        assertEquals(lat, 1.0, 0.0)
        assertEquals(lon, 2.0, 0.0)
    }
}
