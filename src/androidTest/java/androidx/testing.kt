/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.support.annotation.LayoutRes
import android.util.AttributeSet
import android.util.Xml
import androidx.graphics.drawable.toBitmap
import com.google.common.truth.ThrowableSubject
import com.google.common.truth.Truth.assertThat
import org.xmlpull.v1.XmlPullParser
import java.io.ByteArrayOutputStream
import java.util.Arrays

inline fun <reified T : Throwable> assertThrows(body: () -> Unit): ThrowableSubject {
    try {
        body()
    } catch (e: Throwable) {
        if (e is T) {
            return assertThat(e)
        }
        throw e
    }
    throw AssertionError("Body completed successfully. Expected ${T::class.java.simpleName}.")
}

fun fail(message: String? = null): Nothing = throw AssertionError(message)

@SuppressLint("ResourceType")
fun Context.getAttributeSet(@LayoutRes layoutId: Int): AttributeSet {
    val parser = resources.getXml(layoutId)
    var type = parser.next()
    while (type != XmlPullParser.START_TAG) {
        type = parser.next()
    }
    return Xml.asAttributeSet(parser)
}

fun <T : Drawable> T.bytesEqualTo(t: T?) = toBitmap().bytesEqualTo(t?.toBitmap(), true)

fun <T : Drawable> T.pixelsEqualTo(t: T?) = toBitmap().pixelsEqualTo(t?.toBitmap(), true)

fun Bitmap.bytesEqualTo(otherBitmap: Bitmap?, shouldRecycle: Boolean = false) =
    otherBitmap?.let { other ->
        if (width == other.width && height == other.height) {
            val res = toBytes().contentEquals(other.toBytes())
            if (shouldRecycle) {
                doRecycle().also { otherBitmap.doRecycle() }
            }
            res
        } else false
    } ?: run { false }

fun Bitmap.pixelsEqualTo(otherBitmap: Bitmap?, shouldRecycle: Boolean = false) =
    otherBitmap?.let { other ->
        if (width == other.width && height == other.height) {
            val res = Arrays.equals(toPixels(), other.toPixels())
            if (shouldRecycle) {
                doRecycle().also { otherBitmap.doRecycle() }
            }
            res
        } else false
    } ?: run { false }

private fun Bitmap.toBytes(): ByteArray = ByteArrayOutputStream().use { stream ->
    compress(Bitmap.CompressFormat.JPEG, 100, stream)
    stream.toByteArray()
}

private fun Bitmap.doRecycle() {
    if (!isRecycled) recycle()
}

private fun Bitmap.toPixels() =
    IntArray(width * height).apply {
        getPixels(this, 0, width, 0, 0, width, height)
    }