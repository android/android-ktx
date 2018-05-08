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

@SdkSuppress(minSdkVersion = 19)
class ArrayMapTest {
    @Test fun empty() {
        val map = arrayMapOf<String, String>()
        assertEquals(0, map.size)
    }

    @Test fun nonEmpty() {
        val map = arrayMapOf("foo" to "bar", "bar" to "baz")
        assertThat(map).containsExactly("foo", "bar", "bar", "baz")
    }

    @Test fun duplicateKeyKeepsLast() {
        val map = arrayMapOf("foo" to "bar", "foo" to "baz")
        assertThat(map).containsExactly("foo", "baz")
    }
}
