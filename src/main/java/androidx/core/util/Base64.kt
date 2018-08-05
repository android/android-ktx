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

package androidx.core.util

import android.util.Base64
import java.nio.charset.Charset
import kotlin.text.Charsets.US_ASCII
import kotlin.text.Charsets.UTF_8

/**
 * Encode this [String] to its base-64 ASCII string representation.
 *
 * @see Base64.encode
 */
inline fun String.encodeBase64(
    flags: Int = Base64.DEFAULT,
    charset: Charset = UTF_8
): String = Base64.encode(toByteArray(charset), flags).toString(US_ASCII)

/**
 * Encode this [ByteArray] to its base-64 ASCII string representation.
 *
 * @see Base64.encode
 */
inline fun ByteArray.encodeBase64(
    flags: Int = Base64.DEFAULT,
    offset: Int = 0,
    length: Int = size
): String = Base64.encode(this, offset, length, flags).toString(US_ASCII)

/**
 * Decode this base-64 encoded ASCII [String] to its original bytes.
 *
 * @see Base64.decode
 */
inline fun String.decodeBase64(
    flags: Int = Base64.DEFAULT
): ByteArray = Base64.decode(toByteArray(US_ASCII), flags)

/**
 * Decode this base-64 encoded [ByteArray] to its original bytes.
 *
 * @see Base64.decode
 */
inline fun ByteArray.decodeBase64(
    flags: Int = Base64.DEFAULT,
    offset: Int = 0,
    length: Int = size
): ByteArray = Base64.decode(this, offset, length, flags)
