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

package androidx.os.persistable

import android.os.Build.VERSION_CODES
import android.os.PersistableBundle
import android.support.annotation.RequiresApi

// Scalars
@RequiresApi(VERSION_CODES.LOLLIPOP_MR1)
infix fun String.to(value: Boolean) = PersistableBundlePair({ putBoolean(this@to, value) })

@RequiresApi(VERSION_CODES.LOLLIPOP_MR1)
infix fun String.to(value: Double) = PersistableBundlePair({ putDouble(this@to, value) })

@RequiresApi(VERSION_CODES.LOLLIPOP_MR1)
infix fun String.to(value: Int) = PersistableBundlePair({ putInt(this@to, value) })

@RequiresApi(VERSION_CODES.LOLLIPOP_MR1)
infix fun String.to(value: Long) = PersistableBundlePair({ putLong(this@to, value) })

@RequiresApi(VERSION_CODES.LOLLIPOP_MR1)
infix fun String.to(value: String) = PersistableBundlePair({ putString(this@to, value) })

@Suppress("UNUSED_PARAMETER")
@RequiresApi(VERSION_CODES.LOLLIPOP_MR1)
infix fun String.to(value: Nothing?) = PersistableBundlePair({ putString(this@to, null) })


// Scalar Array
@RequiresApi(VERSION_CODES.LOLLIPOP_MR1)
infix fun String.to(value: BooleanArray) = PersistableBundlePair({ putBooleanArray(this@to, value) })
@RequiresApi(VERSION_CODES.LOLLIPOP_MR1)
infix fun String.to(value: DoubleArray) = PersistableBundlePair({ putDoubleArray(this@to, value) })
@RequiresApi(VERSION_CODES.LOLLIPOP_MR1)
infix fun String.to(value: IntArray) = PersistableBundlePair({ putIntArray(this@to, value) })
@RequiresApi(VERSION_CODES.LOLLIPOP_MR1)
infix fun String.to(value: LongArray) = PersistableBundlePair({ putLongArray(this@to, value) })


// Reference Arrays
@RequiresApi(VERSION_CODES.LOLLIPOP)
infix fun String.to(value: Array<String>) = PersistableBundlePair({ putStringArray(this@to, value) })

/**
 * Marker function for invalid arrays
 */
@Suppress("unused", "UNUSED_PARAMETER")
@RequiresApi(VERSION_CODES.LOLLIPOP_MR1)
infix fun <T> String.to(value: Array<T>) = ArrayNotValid

object ArrayNotValid

data class PersistableBundlePair(val putFunction: PersistableBundle.() -> Unit)
