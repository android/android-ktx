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

package androidx.delegate

import java.lang.ref.PhantomReference
import java.lang.ref.ReferenceQueue
import java.lang.ref.SoftReference
import java.lang.ref.WeakReference
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Wrap the [SoftReference] by kotlin delegate. Don't have to use [get]/[set] method
 * for accessing or assigning a variable each of times.
 *
 * ```
 * val data by SoftRef(TestData())
 * ```
 */
class SoftRef<T>(
    default: T? = null,
    private val queue: ReferenceQueue<T>? = null
) : ReadWriteProperty<Any?, T?> {
    private var variable: SoftReference<T>?

    init {
        variable = initSoftReference(default, queue)
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T? = variable?.get()

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
        variable = initSoftReference(value, queue)
    }

    private fun initSoftReference(value: T? = null, queue: ReferenceQueue<T>? = null) =
        value?.run {
            queue?.let { SoftReference(this, it) } ?: SoftReference(this)
        }
}

/**
 * Wrap the [WeakReference] by kotlin delegate. Don't have to use [get]/[set] method
 * for accessing or assigning a variable each of times.
 *
 * ```
 * val data by SoftRef(TestData())
 * ```
 */
class WeakRef<T>(
    default: T? = null,
    private val queue: ReferenceQueue<T>? = null
) : ReadWriteProperty<Any?, T?> {
    private var variable: WeakReference<T>?

    init {
        variable = initWeakReference(default, queue)
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T? = variable?.get()

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
        variable = initWeakReference(value, queue)
    }

    private fun initWeakReference(value: T? = null, queue: ReferenceQueue<T>? = null) =
        value?.run {
            queue?.let { WeakReference(this, it) } ?: WeakReference(this)
        }
}

/**
 * Wrap the [PhantomReference] by kotlin delegate. Don't have to use [get]/[set] method
 * for accessing or assigning a variable each of times.
 *
 * ```
 * val data by SoftRef(TestData())
 * ```
 */
class PhantomRef<T>(
    default: T? = null,
    private val queue: ReferenceQueue<T>
) : ReadWriteProperty<Any?, T?> {
    private var variable: PhantomReference<T>?

    init {
        variable = default?.let { PhantomReference(it, queue) }
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T? = variable?.get()

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
        variable = value?.let { PhantomReference(it, queue) }
    }
}