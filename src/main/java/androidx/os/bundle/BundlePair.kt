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

package androidx.os.bundle

import android.os.Binder
import android.os.Build.VERSION_CODES
import android.os.Bundle
import android.os.Parcelable
import android.support.annotation.RequiresApi
import android.util.Size
import android.util.SizeF
import java.io.Serializable

// Scalars
infix fun String.to(value: Boolean) = BundlePair({ putBoolean(this@to, value) })
infix fun String.to(value: Byte) = BundlePair({ putByte(this@to, value) })
infix fun String.to(value: Char) = BundlePair({ putChar(this@to, value) })
infix fun String.to(value: Double) = BundlePair({ putDouble(this@to, value) })
infix fun String.to(value: Float) = BundlePair({ putFloat(this@to, value) })
infix fun String.to(value: Int) = BundlePair({ putInt(this@to, value) })
infix fun String.to(value: Long) = BundlePair({ putLong(this@to, value) })
infix fun String.to(value: Short) = BundlePair({ putShort(this@to, value) })
infix fun String.to(value: String) = BundlePair({ putString(this@to, value) })

// References
infix fun String.to(value: Bundle) = BundlePair({ putBundle(this@to, value) })
infix fun String.to(value: CharSequence) = BundlePair({ putCharSequence(this@to, value) })
infix fun String.to(value: Parcelable) = BundlePair({ putParcelable(this@to, value) })
@Suppress("UNUSED_PARAMETER")
infix fun String.to(value: Nothing?) = BundlePair({ putString(this@to, null) })
infix fun <T : Serializable> String.to(value: T) = BundlePair({ putSerializable(this@to, value) })

// compat References
@RequiresApi(VERSION_CODES.JELLY_BEAN_MR2)
infix fun String.to(value: Binder) = BundlePair({ putBinder(this@to, value) })

@RequiresApi(VERSION_CODES.LOLLIPOP)
infix fun String.to(value: Size) = BundlePair({ putSize(this@to, value) })

@RequiresApi(VERSION_CODES.LOLLIPOP)
infix fun String.to(value: SizeF) = BundlePair({ putSizeF(this@to, value) })

// Scalar Array
infix fun String.to(value: BooleanArray) = BundlePair({ putBooleanArray(this@to, value) })
infix fun String.to(value: ByteArray) = BundlePair({ putByteArray(this@to, value) })
infix fun String.to(value: CharArray) = BundlePair({ putCharArray(this@to, value) })
infix fun String.to(value: DoubleArray) = BundlePair({ putDoubleArray(this@to, value) })
infix fun String.to(value: FloatArray) = BundlePair({ putFloatArray(this@to, value) })
infix fun String.to(value: IntArray) = BundlePair({ putIntArray(this@to, value) })
infix fun String.to(value: LongArray) = BundlePair({ putLongArray(this@to, value) })
infix fun String.to(value: ShortArray) = BundlePair({ putShortArray(this@to, value) })

// Reference Arrays
infix fun <T : Parcelable> String.to(value: Array<T>) = BundlePair({ putParcelableArray(this@to, value) })
infix fun String.to(value: Array<String>) = BundlePair({ putStringArray(this@to, value) })
infix fun <T : CharSequence> String.to(value: Array<T>) = BundlePair({ putCharSequenceArray(this@to, value) })
infix fun <T : Serializable> String.to(value: Array<T>) = BundlePair({ putSerializable(this@to, value) })

/**
 * Marker function for invalid arrays. Valid arrays are covered by explicit [to] implementations.
 */
@Suppress("unused", "UNUSED_PARAMETER")
infix fun <T> String.to(value: Array<T>) = ArrayNotValid

/**
 * Marker object for invalid array types
 */
object ArrayNotValid

class BundlePair internal constructor(internal val putFunction: Bundle.() -> Unit)