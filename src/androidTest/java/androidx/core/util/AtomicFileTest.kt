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
import android.util.AtomicFile
import androidx.testutils.assertThrows
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.io.IOException

@SdkSuppress(minSdkVersion = 17)
class AtomicFileTest {
    @get:Rule val temporaryFolder = TemporaryFolder()

    private lateinit var file: AtomicFile

    @Before fun before() {
        file = AtomicFile(temporaryFolder.newFile())
    }

    @Test fun tryWriteSuccess() {
        file.tryWrite {
            it.write(byteArrayOf(0, 1, 2))
        }
        val bytes = file.openRead().use { it.readBytes() }
        assertArrayEquals(byteArrayOf(0, 1, 2), bytes)
    }

    @Test fun tryWriteFail() {
        val os = file.startWrite()
        os.write(byteArrayOf(0, 1, 2))
        file.finishWrite(os)

        val failure = IOException("Broken!")
        assertThrows<IOException> {
            file.tryWrite {
                it.write(byteArrayOf(3, 4, 5))
                throw failure
            }
        }.isSameAs(failure)

        val bytes = file.openRead().use { it.readBytes() }
        assertArrayEquals(byteArrayOf(0, 1, 2), bytes)
    }

    @Test fun writeBytes() {
        file.writeBytes(byteArrayOf(0, 1, 2))

        val bytes = file.openRead().use { it.readBytes() }
        assertArrayEquals(byteArrayOf(0, 1, 2), bytes)
    }

    @Test fun writeText() {
        file.writeText("Hey")

        val bytes = file.openRead().use { it.readBytes() }
        assertArrayEquals(byteArrayOf(72, 101, 121), bytes)
    }

    @Test fun writeTextCharset() {
        file.writeText("Hey", charset = Charsets.UTF_16LE)

        val bytes = file.openRead().use { it.readBytes() }
        assertArrayEquals(byteArrayOf(72, 0, 101, 0, 121, 0), bytes)
    }

    @Test fun readBytes() {
        val os = file.startWrite()
        os.write(byteArrayOf(0, 1, 2))
        file.finishWrite(os)

        assertArrayEquals(byteArrayOf(0, 1, 2), file.readBytes())
    }

    @Test fun readText() {
        val os = file.startWrite()
        os.write(byteArrayOf(72, 101, 121))
        file.finishWrite(os)

        assertEquals("Hey", file.readText())
    }

    @Test fun readTextCharset() {
        val os = file.startWrite()
        os.write(byteArrayOf(72, 0, 101, 0, 121, 0))
        file.finishWrite(os)

        assertEquals("Hey", file.readText(charset = Charsets.UTF_16LE))
    }
}
