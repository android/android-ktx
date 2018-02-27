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

package androidx.text

import org.junit.Assert.assertEquals
import org.junit.Test

class StringTest {

    @Test
    fun htmlEncode_should_html_encode_string() {
        val target = "htmlEncode <> & \' \""
        val expected = "htmlEncode &lt;&gt; &amp; &#39; &quot;"

        assertEquals(expected, target.htmlEncode())
    }
}