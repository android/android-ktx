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

import android.content.Context
import android.support.annotation.LayoutRes
import android.util.AttributeSet
import android.util.Xml
import com.google.common.truth.ThrowableSubject
import com.google.common.truth.Truth.assertThat
import org.xmlpull.v1.XmlPullParser

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

fun Context.getAttributeSet(@LayoutRes layoutId: Int): AttributeSet {
    val parser = resources.getXml(layoutId)
    var type = parser.next()
    while (type != XmlPullParser.START_TAG) {
        type = parser.next()
    }
    return Xml.asAttributeSet(parser)
}
