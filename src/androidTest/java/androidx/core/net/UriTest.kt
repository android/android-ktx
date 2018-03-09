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

package androidx.core.net

import android.net.Uri
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.File

class UriTest {
    @Test fun uriFromString() {
        val string = "https://test.example.com/foo?bar#baz"
        assertEquals(Uri.parse(string), string.toUri())
    }

    @Test fun uriFromFile() {
        val file = File("/path/to/my/file")
        assertEquals(Uri.fromFile(file), file.toUri())
    }

    @Test fun fileFromUri() {
        val uri = Uri.parse("path/to/my/file")
        assertEquals(File(uri.path), uri.toFile())
    }
}
