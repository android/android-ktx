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

@file:Suppress("NOTHING_TO_INLINE") // Aliases to public API.

package androidx.core.net

import android.content.ContentUris
import android.net.Uri
import java.io.File

/**
 * Creates a Uri from the given encoded URI string.
 *
 * @see Uri.parse
 */
inline fun String.toUri(): Uri = Uri.parse(this)

/**
 * Creates a Uri from the given file.
 *
 * @see Uri.fromFile
 */
inline fun File.toUri(): Uri = Uri.fromFile(this)

/** Creates a [File] from the given [Uri]. */
inline fun Uri.toFile(): File = File(path)

/**
 * Appends the given ID to the end of the path.
 *
 * @param id to append
 *
 * @return the builder
 */
inline fun Uri.Builder.appendId(id: Long): Uri.Builder = ContentUris.appendId(this, id)

/**
 * Appends the given ID to the end of the path.
 *
 * @param id to append
 *
 * @return a new URI with the given ID appended to the end of the path
 */
inline fun Uri.withAppendedId(id: Long): Uri = ContentUris.withAppendedId(this, id)

/**
 * Converts the last path segment to a long.
 *
 * <p>This supports a common convention for content URIs where an ID is
 * stored in the last segment.
 *
 * @throws UnsupportedOperationException if this isn't a hierarchical URI
 * @throws NumberFormatException if the last segment isn't a number
 *
 * @return the long conversion of the last segment or -1 if the path is
 *  empty
 */
inline fun Uri.parseId(): Long = ContentUris.parseId(this)
