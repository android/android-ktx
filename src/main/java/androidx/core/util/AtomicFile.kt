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

@file:Suppress("NOTHING_TO_INLINE") // Aliases to other public API.

package androidx.core.util

import android.util.AtomicFile
import androidx.annotation.RequiresApi
import java.io.FileOutputStream
import java.nio.charset.Charset

/**
 * Perform the write operations inside [block] on this file. If [block] throws an exception the
 * write will be failed. Otherwise the write will be applied atomically to the file.
 */
@RequiresApi(17)
inline fun AtomicFile.tryWrite(block: (out: FileOutputStream) -> Unit) {
    val stream = startWrite()
    var success = false
    try {
        block(stream)
        success = true
    } finally {
        if (success) {
            finishWrite(stream)
        } else {
            failWrite(stream)
        }
    }
}

/**
 * Sets the content of this file as an [array] of bytes.
 */
@RequiresApi(17)
fun AtomicFile.writeBytes(array: ByteArray) {
    tryWrite {
        it.write(array)
    }
}

/**
 * Sets the content of this file as [text] encoded using UTF-8 or specified [charset].
 * If this file exists, it becomes overwritten.
 */
@RequiresApi(17)
fun AtomicFile.writeText(text: String, charset: Charset = Charsets.UTF_8) {
    writeBytes(text.toByteArray(charset))
}

/**
 * Gets the entire content of this file as a byte array.
 *
 * This method is not recommended on huge files. It has an internal limitation of 2 GB file size.
 */
@RequiresApi(17)
inline fun AtomicFile.readBytes(): ByteArray = readFully()

/**
 * Gets the entire content of this file as a String using UTF-8 or specified [charset].
 *
 * This method is not recommended on huge files. It has an internal limitation of 2 GB file size.
 */
@RequiresApi(17)
fun AtomicFile.readText(charset: Charset = Charsets.UTF_8): String {
    return readFully().toString(charset)
}
