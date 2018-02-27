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
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test


class CharSequenceTest {

    @Test
    fun isDigitsOnly_should_return_true_if_only_digits() {
        val digits = "012345"

        val result = digits.isDigitsOnly()

        assertTrue(result)
    }

    @Test
    fun isDigitsOnly_should_return_false_if_contains_non_digits() {
        val nonDigits = "0123 abc"

        val result = nonDigits.isDigitsOnly()

        assertFalse(result)
    }

    @Test
    fun trimmedLength_should_return_length_of_trimmed_string() {
        val afterPadding = "string    "
        val beforePadding = "    string"
        val noPadding = "string"

        val expectedLength = noPadding.length

        assertEquals(expectedLength, afterPadding.trimmedLength())
        assertEquals(expectedLength, beforePadding.trimmedLength())
        assertEquals(expectedLength, noPadding.trimmedLength())
    }

    @Test
    fun trimmedLength_should_return_0_for_empty_string() {
        val emptyString = ""

        assertEquals(emptyString.trimmedLength(), 0)
    }

}