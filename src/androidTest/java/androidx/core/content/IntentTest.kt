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

package androidx.core.content

import android.content.Intent
import android.net.Uri
import androidx.core.os.bundleOf
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class IntentTest {

    @Test
    fun intentOf() {
        val data = Uri.parse("uri")

        val intent = intentOf(
            action = "action",
            data = data,
            type = "type",
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK,
            categories = setOf("category1", "category2"),
            extras = bundleOf("key1" to "value1", "key2" to "value2")
        )

        assertEquals("action", intent.action)
        assertEquals(data, intent.data)
        assertEquals(Intent.FLAG_ACTIVITY_CLEAR_TASK, intent.flags)
        assertEquals("type", intent.type)
        assertTrue(intent.hasCategory("category1"))
        assertTrue(intent.hasCategory("category2"))
        assertTrue(intent.hasExtra("key1"))
        assertTrue(intent.hasExtra("key2"))
    }
}