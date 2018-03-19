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

package androidx.core.io

import org.junit.Assert.assertNull
import org.junit.Assert.assertSame
import org.junit.Test
import java.io.File

class FileTest {

    @Test fun jpg() {
        val photo = File("sunset.jpg")
        val type = photo.getMimeType()
        assertSame("image/jpeg", type)
    }

    @Test fun png() {
        val logo = File("images/logo.png")
        val type = logo.getMimeType()
        assertSame("image/png", type)
    }

    @Test fun noExtension() {
        val path = File("foo/bar")
        val type = path.getMimeType()
        assertNull(type)
    }

    @Test fun unknownExtension() {
        val unknownFile = File("foo/bar.baz")
        val type = unknownFile.getMimeType()
        assertNull(type)
    }
}