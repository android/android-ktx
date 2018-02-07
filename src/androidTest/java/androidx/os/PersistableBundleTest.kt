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

package androidx.os

import android.os.Build.VERSION_CODES.LOLLIPOP
import android.os.Build.VERSION_CODES.LOLLIPOP_MR1
import android.support.test.filters.SdkSuppress
import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

@SdkSuppress(minSdkVersion = LOLLIPOP)
class PersistableBundleTest {
    @Test fun persistableBundleOfValid() {
        val bundle = persistableBundleOf(
            "null" persistTo null,

            "double" persistTo 1.0,
            "int" persistTo 1,
            "long" persistTo 1L,

            "string" persistTo "hey",

            "doubleArray" persistTo doubleArrayOf(),
            "intArray" persistTo intArrayOf(),
            "longArray" persistTo longArrayOf(),

            "stringArray" persistTo arrayOf("hey")
        )

        assertEquals(9, bundle.size())

        assertNull(bundle["null"])

        assertEquals(1.0, bundle["double"])
        assertEquals(1, bundle["int"])
        assertEquals(1L, bundle["long"])

        assertEquals("hey", bundle["string"])

        assertArrayEquals(doubleArrayOf(), bundle["doubleArray"] as DoubleArray, 0.0)
        assertArrayEquals(intArrayOf(), bundle["intArray"] as IntArray)
        assertArrayEquals(longArrayOf(), bundle["longArray"] as LongArray)

        assertThat(bundle["stringArray"] as Array<*>).asList().containsExactly("hey")
    }

    @SdkSuppress(minSdkVersion = LOLLIPOP_MR1)
    @Test fun persistableBundleOfValidApi22() {
        val bundle = persistableBundleOf(
            "boolean" persistTo true,
            "booleanArray" persistTo booleanArrayOf()
        )

        assertEquals(true, bundle["boolean"])
        assertArrayEquals(booleanArrayOf(), bundle["booleanArray"] as BooleanArray)
    }

}
