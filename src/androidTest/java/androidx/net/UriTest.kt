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

package androidx.net

import android.net.Uri
import org.junit.Assert.assertEquals
import org.junit.Test

class UriTest {
    @Test
    fun uri() {
        val string = "https://test.example.com/foo?bar#baz"
        assertEquals(Uri.parse(string), string.toUri())
    }

    @Test
    fun appendQueryParameters() {
        val uri1 = Uri.parse("https://test.example.com/foo")
        val appendUri1 = uri1.buildUpon().appendQueryParameters(
                "key1" to "value1",
                "key2" to "value2"
        ).build()
        assertEquals("$uri1?key1=value1&key2=value2", appendUri1.toString())

        val uri2 = Uri.parse("https://test.example.com/foo?key1=value1")
        val appendUri2 = uri2.buildUpon().appendQueryParameters(
                "key2" to "value2"
        ).build()
        assertEquals("$uri2&key2=value2", appendUri2.toString())

    }
}
