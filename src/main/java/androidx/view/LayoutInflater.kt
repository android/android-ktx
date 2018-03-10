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

@file:Suppress("NOTHING_TO_INLINE", "EXTENSION_SHADOWED_BY_MEMBER")

package androidx.view

import android.support.annotation.LayoutRes
import android.view.InflateException
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.xmlpull.v1.XmlPullParser

/**
 * Inflate a new [View] hierarchy from the specified XML resource.
 *
 * @throws [InflateException] if there is an error.
 */
inline fun <reified T : View> LayoutInflater.inflate(
    @LayoutRes resource: Int,
    root: ViewGroup?,
    attachToRoot: Boolean = false
): T = inflate(resource, root, attachToRoot) as T

/**
 * Inflate a new [View] hierarchy from the specified XML node.
 *
 * @throws [InflateException] if there is an error.
 */
inline fun <reified T : View> LayoutInflater.inflate(
    parser: XmlPullParser,
    root: ViewGroup?,
    attachToRoot: Boolean = false
): T = inflate(parser, root, attachToRoot) as T
