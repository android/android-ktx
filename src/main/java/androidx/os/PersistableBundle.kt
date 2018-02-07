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

package androidx.os

import android.os.Build.VERSION_CODES.LOLLIPOP
import android.os.Build.VERSION_CODES.LOLLIPOP_MR1
import android.os.PersistableBundle
import android.support.annotation.RequiresApi

/**
 * Returns a new [PersistableBundle] with the given key/value pairs as elements.
 *
 * @throws IllegalArgumentException When a value is not a supported type of [PersistableBundle].
 */
@RequiresApi(LOLLIPOP)
fun persistableBundleOf(vararg pairs: PersistableBundlePair) = PersistableBundle(pairs.size).apply {
    pairs.forEach { it.putFunction(this) }
}


// Scalars
@RequiresApi(LOLLIPOP_MR1)
infix fun String.persistTo(value: Boolean) = PersistableBundlePair({ putBoolean(this@persistTo, value) })

@RequiresApi(LOLLIPOP_MR1)
infix fun String.persistTo(value: Double) = PersistableBundlePair({ putDouble(this@persistTo, value) })

@RequiresApi(LOLLIPOP_MR1)
infix fun String.persistTo(value: Int) = PersistableBundlePair({ putInt(this@persistTo, value) })

@RequiresApi(LOLLIPOP_MR1)
infix fun String.persistTo(value: Long) = PersistableBundlePair({ putLong(this@persistTo, value) })

@RequiresApi(LOLLIPOP_MR1)
infix fun String.persistTo(value: String) = PersistableBundlePair({ putString(this@persistTo, value) })

@Suppress("UNUSED_PARAMETER")
@RequiresApi(LOLLIPOP_MR1)
infix fun String.persistTo(value: Nothing?) = PersistableBundlePair({ putString(this@persistTo, null) })

// Scalar Array
@RequiresApi(LOLLIPOP_MR1)
infix fun String.persistTo(value: BooleanArray) = PersistableBundlePair({ putBooleanArray(this@persistTo, value) })
@RequiresApi(LOLLIPOP_MR1)
infix fun String.persistTo(value: DoubleArray) = PersistableBundlePair({ putDoubleArray(this@persistTo, value) })
@RequiresApi(LOLLIPOP_MR1)
infix fun String.persistTo(value: IntArray) = PersistableBundlePair({ putIntArray(this@persistTo, value) })
@RequiresApi(LOLLIPOP_MR1)
infix fun String.persistTo(value: LongArray) = PersistableBundlePair({ putLongArray(this@persistTo, value) })

// Reference Arrays
@RequiresApi(LOLLIPOP)
infix fun String.persistTo(value: Array<String>) = PersistableBundlePair({ putStringArray(this@persistTo, value) })

/**
 * Marker function for invalid arrays. Valid arrays are covered by explicit [to] implementations.
 */
@Suppress("unused", "UNUSED_PARAMETER")
@RequiresApi(LOLLIPOP_MR1)
infix fun <T> String.persistTo(value: Array<T>) = ArrayContainsInvalidType

data class PersistableBundlePair(val putFunction: PersistableBundle.() -> Unit)