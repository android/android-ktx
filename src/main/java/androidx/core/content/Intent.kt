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

@file:Suppress("NOTHING_TO_INLINE")

package androidx.core.content

import android.content.Intent
import android.net.Uri
import android.os.Bundle

/**
 * Creates an [Intent] with explicitly specified parameters
 *
 * @see [Intent.setAction]
 * @see [Intent.setData]
 * @see [Intent.setType]
 * @see [Intent.setDataAndType]
 * @see [Intent.addCategory]
 * @see [Intent.putExtras]
 */
inline fun intentOf(
    action: String? = null,
    data: Uri? = null,
    type: String? = null,
    flags: Int? = null,
    categories: Set<String> = emptySet(),
    extras: Bundle? = null
): Intent {
    val intent = Intent()
    action?.let(intent::setAction)

    if (data != null && type != null) {
        intent.setDataAndType(data, type)
    } else {
        data?.let(intent::setData)
        type?.let(intent::setType)
    }

    flags?.let(intent::setFlags)
    extras?.let(intent::putExtras)

    categories.forEach { intent.addCategory(it) }
    return intent
}