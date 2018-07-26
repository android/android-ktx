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

@file:Suppress("NOTHING_TO_INLINE")

package androidx.util

import android.util.Base64
import java.nio.charset.Charset

/**
 * Convert [String] to base-64 encoded [String].
 */
inline fun String.encodeBase64(
    offset: Int = 0,
    len: Int = this.length,
    flags: Int = Base64.DEFAULT,
    byteArrayCharset: Charset = Charsets.US_ASCII,
    stringCharset: Charset = Charsets.UTF_8
): String = Base64.encode(toByteArray(byteArrayCharset), offset, len, flags).toString(stringCharset)

/**
 * Convert [ByteArray] to base-64 encoded [ByteArray].
 */
inline fun ByteArray.encodeBase64(
    offset: Int = 0,
    len: Int = this.size,
    flags: Int = Base64.DEFAULT
): ByteArray = Base64.encode(this, offset, len, flags)

/**
 * Convert base-64 encoded [String] to decoded [String].
 */
inline fun String.Companion.decodeBase64(
    input: String,
    offset: Int = 0,
    len: Int = input.length,
    flags: Int = Base64.DEFAULT,
    byteArrayCharset: Charset = Charsets.US_ASCII,
    stringCharset: Charset = Charsets.UTF_8
): String =
    Base64.decode(input.toByteArray(byteArrayCharset), offset, len, flags).toString(stringCharset)

/**
 * Convert base-64 encoded [ByteArray] to decoded [ByteArray].
 */
inline fun ByteArray.decodeBase64(
    offset: Int = 0,
    len: Int = this.size,
    flags: Int = Base64.DEFAULT
): ByteArray = Base64.decode(this, offset, len, flags)
