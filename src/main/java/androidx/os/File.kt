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

package androidx.os

import android.net.Uri
import java.io.File
import android.support.v4.content.FileProvider
import android.content.Context
/**
 * Creates a Uri from the given file
 * Uri.fromFile() throw FileUriExposedException on API 24+
 * @see Uri.parse
 */
@RequiresApi(24)
inline fun File.toUri(context: Context, authority: String): Uri = FileProvider.getUriForFile(context, authority, this)

/**
 * Creates a Uri from the given file.
 *
 * @see Uri.parse
 */
inline fun File.toUri(): Uri = Uri.fromFile(this)
