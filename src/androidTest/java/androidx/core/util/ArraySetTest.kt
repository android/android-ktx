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

package androidx.core.util

import android.support.test.filters.SdkSuppress
import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertEquals
import org.junit.Test

@SdkSuppress(minSdkVersion = 23)
class ArraySetTest {
    @Test fun empty() {
        val set = arraySetOf<String>()
        assertEquals(0, set.size)
    }

    @Test fun nonEmpty() {
        val set = arraySetOf("foo", "bar", "baz")
        assertThat(set).containsExactly("foo", "bar", "baz")
    }
}
