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

package androidx.core.content

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.content.SharedPreferences
import android.content.res.TypedArray
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.annotation.AttrRes
import android.support.annotation.RequiresApi
import android.support.annotation.StyleRes
import android.util.AttributeSet
import androidx.app.ActivityIntentExtraDelegate
import androidx.content.delegate
import androidx.core.os.get
import androidx.core.os.set
import java.io.Serializable
import java.util.ArrayList
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Return the handle to a system-level service by class.
 *
 * The return type of this function intentionally uses a
 * [platform type](https://kotlinlang.org/docs/reference/java-interop.html#null-safety-and-platform-types)
 * to allow callers to decide whether they require a service be present or can tolerate its absence.
 *
 * @see Context.getSystemService(Class)
 */
@RequiresApi(23)
@Suppress("HasPlatformType") // Intentionally propagating platform type with unknown nullability.
inline fun <reified T> Context.systemService() = getSystemService(T::class.java)

/**
 * Executes [block] on a [TypedArray] receiver. The [TypedArray] holds the attribute
 * values in [set] that are listed in [attrs]. In addition, if the given [AttributeSet]
 * specifies a style class (through the `style` attribute), that style will be applied
 * on top of the base attributes it defines.
 *
 * @param set The base set of attribute values.
 * @param attrs The desired attributes to be retrieved. These attribute IDs must be
 *              sorted in ascending order.
 * @param defStyleAttr An attribute in the current theme that contains a reference to
 *                     a style resource that supplies defaults values for the [TypedArray].
 *                     Can be 0 to not look for defaults.
 * @param defStyleRes A resource identifier of a style resource that supplies default values
 *                    for the [TypedArray], used only if [defStyleAttr] is 0 or can not be found
 *                     in the theme. Can be 0 to not look for defaults.
 *
 * @see Context.obtainStyledAttributes
 * @see android.content.res.Resources.Theme.obtainStyledAttributes
 */
inline fun Context.withStyledAttributes(
    set: AttributeSet? = null,
    attrs: IntArray,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = 0,
    block: TypedArray.() -> Unit
) {
    val typedArray = obtainStyledAttributes(set, attrs, defStyleAttr, defStyleRes)
    try {
        typedArray.block()
    } finally {
        typedArray.recycle()
    }
}

/**
 * Executes [block] on a [TypedArray] receiver. The [TypedArray] holds the the values
 * defined by the style resource [resourceId] which are listed in [attrs].
 *
 * @param attrs The desired attributes. These attribute IDs must be sorted in ascending order.
 *
 * @see Context.obtainStyledAttributes
 * @see android.content.res.Resources.Theme.obtainStyledAttributes
 */
inline fun Context.withStyledAttributes(
    @StyleRes resourceId: Int,
    attrs: IntArray,
    block: TypedArray.() -> Unit
) {
    val typedArray = obtainStyledAttributes(resourceId, attrs)
    try {
        typedArray.block()
    } finally {
        typedArray.recycle()
    }
}

fun <T: Any> bindPreference(key: String, defaultValue: T? = null, preferenceName: String? = null, mode: Int = 0):
        SharedPreferencesDelegate<T> {
    return SharedPreferencesDelegate(preferenceName, mode, key, defaultValue)
}

class SharedPreferencesDelegate<T: Any>(
    private val preferenceName: String? = null,
    private val mode: Int = 0,
    val key: String,
    val defaultValue: T?):
    SharedPreferencesProperty<T> {

    override fun getValue(thisRef: Context, property: KProperty<*>): T? {
        return if(preferenceName != null)
            thisRef.getSharedPreferences(preferenceName, mode)[key, defaultValue]
        else PreferenceManager.getDefaultSharedPreferences(thisRef)[key, defaultValue]
    }

    override fun setValue(thisRef: Context, property: KProperty<*>, value: T?) {
        if(preferenceName != null)
            thisRef.getSharedPreferences(preferenceName, mode)[key] = value
        else PreferenceManager.getDefaultSharedPreferences(thisRef)[key] = value
    }
}

interface SharedPreferencesProperty<T> {
    /**
     * Returns the value of the property for the given object.
     * @param thisRef the object for which the value is requested.
     * @param property the metadata for the property.
     * @return the property value.
     */
    operator fun getValue(thisRef: Context, property: KProperty<*>): T?

    /**
     * Sets the value of the property for the given object.
     * @param thisRef the object for which the value is requested.
     * @param property the metadata for the property.
     * @param value the value to set.
     */
    operator fun setValue(thisRef: Context, property: KProperty<*>, value: T?)
}
