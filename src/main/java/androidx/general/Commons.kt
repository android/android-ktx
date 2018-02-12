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

package androidx.general

import java.io.UnsupportedEncodingException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Formats [Long] value of timestamp to [String] using formatting pattern.
 *
 * Throws [IllegalArgumentException] if the object cannot be formatted by this format.
 */
@Throws(IllegalArgumentException::class)
fun Long.formatTimestamp(format: String, locale: Locale = Locale.getDefault()): String =
    SimpleDateFormat(format, locale).format(this)

/**
 * Parses [String] value of date to [Date] object using formatting pattern.
 *
 * Throws [ParseException] if an error occurs during parsing.
 */
@Throws(ParseException::class)
fun String.toDate(formatFrom: String, locale: Locale = Locale.getDefault()): Date =
    SimpleDateFormat(formatFrom, locale).parse(this)

/**
 * Constructor for [String] based on [ByteArray] representation of the [String] and charset[String]
 *
 * Throws [UnsupportedEncodingException] if encoding is unsupported.
 */
@Throws(UnsupportedEncodingException::class)
inline fun String(bytes: ByteArray, charset: String): String =
    java.lang.String(bytes, charset) as String

/**
 * [Boolean] representation of [Int] value
 *
 */
fun Int.toBoolean(): Boolean = this > 0

/**
 * Extension function that checks if boolean value is true and invokes the [functionTrue]
 *
 * ```
 * Example :
 * val isTrue = true
 * isTrue{ println(16) }// = 6
 * ```
 */
inline operator fun Boolean.invoke(functionTrue: Boolean.() -> Unit) {
    if (this) functionTrue(this)
}

/**
 * Extension function that checks if the object == null and executes different functions for both cases with returning value
 *
 * ```
 * Example :
 * var name: String? = null
 * val result = name({5},{6})
 * println(result) // = 6
 * ```
 */
inline operator fun <reified T, reified R> T?.invoke(
    functionTrue: T.() -> R,
    functionFalse: () -> R
): R = if (this != null) functionTrue(this) else functionFalse()

/**
 * Extension function that checks if the boolean value == true and executes different functions for both cases with returning value
 *
 * ```
 * Example :
 * var name: isTrue? = true
 * val result = name({5},{6})
 * println(result) // = 5
 * ```
 */
inline operator fun <reified R> Boolean?.invoke(
    functionTrue: Boolean.() -> R,
    functionFalse: () -> R
): R = if (this == true) functionTrue(this) else functionFalse()