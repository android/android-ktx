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

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import kotlin.reflect.KProperty

/**
 * Allows editing of this preference instance with a call to [apply][SharedPreferences.Editor.apply]
 * or [commit][SharedPreferences.Editor.commit] to persist the changes.
 * Default behaviour is [apply][SharedPreferences.Editor.apply].
 *
 * prefs.edit {
 *     putString("key", value)
 * }
 *
 * To [commit][SharedPreferences.Editor.commit] changes:
 *
 * prefs.edit(commit = true) {
 *     putString("key", value)
 * }
 *
 */
@SuppressLint("ApplySharedPref")
inline fun SharedPreferences.edit(
    commit: Boolean = false,
    action: SharedPreferences.Editor.() -> Unit
) {
    val editor = edit()
    action(editor)
    if (commit) {
        editor.commit()
    } else {
        editor.apply()
    }
}

inline operator fun <reified T : Any> SharedPreferences.get(key: String, defaultValue: T? = null):
        T? = get(T::class.java, key, defaultValue)

inline operator fun <reified T : Any> SharedPreferences.set(key: String, value: T?) {
    set(T::class.java, key, value)
}

inline fun <reified T : Any> SharedPreferences.set(key: String) {
    set<T>(key, null)
}

@Suppress("UNCHECKED_CAST")// Checked by reflection.
operator fun <T : Any> SharedPreferences.get(clazz: Class<T>, key: String, defaultValue: T? = null):
        T? = when (defaultValue) {
        null -> when {
            clazz.isAssignableFrom(String::class.java) -> getString(key, defaultValue) as T?
            clazz.isAssignableFrom(Set::class.java) ->
                when {
                    clazz.componentType.isAssignableFrom(String::class.java) ->
                        getStringSet(key, defaultValue) as T?
                    else -> {
                        throw IllegalArgumentException(
                            "Illegal nullable Set type ${clazz.canonicalName} for key \"$key\"")
                    }
                }
            else ->
                throw IllegalArgumentException("Illegal nullable type ${clazz.canonicalName}" +
                        "for key \"$key\"")
        }
        else -> when (defaultValue) {
            is Boolean -> getBoolean(key, defaultValue) as T
            is Float -> getFloat(key, defaultValue) as T
            is Int -> getInt(key, defaultValue) as T
            is Long -> getLong(key, defaultValue) as T
            is String -> getString(key, defaultValue) as T

            is Set<*> -> {
                val componentType = defaultValue::class.java.componentType
                when {
                    String::class.java.isAssignableFrom(componentType) ->
                        getStringSet(key, defaultValue as Set<String>) as T
                    else -> throw IllegalArgumentException(
                        "Illegal value Set type ${componentType.canonicalName} for key \"$key\"")
                }
            }
            else -> throw IllegalArgumentException(
                "Illegal value type ${defaultValue.javaClass.canonicalName} for key \"$key\"")
        }
}

/***
 * Set a [value] in the [SharedPreferences] for the given [key]
 *
 * @param clazz the class of the preference type
 * @param key the preference key
 * @param value the value to set
 *
 * @throws IllegalArgumentException
 * if [value] is null and [clazz] isn't [String] or [Set] of [String]
 * if [value] isn't null but it's type is not supported by [SharedPreferences]
 */
operator fun <T : Any> SharedPreferences.set(clazz: Class<T>, key: String, value: T?) {
    when (value) {
        null -> when {
            clazz.isAssignableFrom(String::class.java) ->
                edit { putString(key, value) }

            clazz.isAssignableFrom(Set::class.java) ->
                when {
                    String::class.java.isAssignableFrom(clazz.componentType) ->
                        edit { putStringSet(key, value) }
                    else -> throw IllegalArgumentException(
                            "Illegal Set of type ${clazz.canonicalName} for key \"$key\"")
                }
            else -> throw IllegalArgumentException(
                "Illegal nullable value type ${javaClass.canonicalName} for key \"$key\"")
        }
        else -> when (value) {
            is Boolean -> edit { putBoolean(key, value) }
            is Float -> edit { putFloat(key, value) }
            is Int -> edit { putInt(key, value) }
            is Long -> edit { putLong(key, value) }
            is String -> edit { putString(key, value) }

            is Set<*> -> {
                val componentType = value::class.java.componentType
                @Suppress("UNCHECKED_CAST") // Checked by reflection.
                when {
                    componentType.isAssignableFrom(String::class.java) ->
                        edit { putStringSet(key, value as Set<String>) }
                    else -> throw IllegalArgumentException(
                        "Illegal Set of type ${componentType.canonicalName} for key \"$key\"")
                }
            }
            else -> throw IllegalArgumentException(
                "Illegal value type ${value.javaClass.canonicalName} for key \"$key\"")
        }
    }
}

class SharedPreferencesDelegate (
    private val name: String? = null,
    private val mode: Int = Context.MODE_PRIVATE
) {
    operator fun get(thisRef: Context): SharedPreferences =
        if (name != null) thisRef.getSharedPreferences(name, mode)
        else PreferenceManager.getDefaultSharedPreferences(thisRef)
}

class SharedPreferencesProperty<T : Any>(
    private val name: String? = null,
    private val mode: Int = Context.MODE_PRIVATE,
    private val context: Context,
    private val clazz: Class<T>,
    private val key: String,
    private val defaultValue: T?
) {
    operator fun getValue(thisRef: Any, property: KProperty<*>): T? =
        SharedPreferencesDelegate(name, mode)[context][clazz, key, defaultValue]

    operator fun setValue(thisRef: Any, property: KProperty<*>, value: T?) {
        SharedPreferencesDelegate(name, mode)[context][clazz, key] = value
    }
}