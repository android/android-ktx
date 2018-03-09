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

package androidx.content

import android.content.Context
import android.content.Intent
import androidx.os.bundleOf

/** Build an intent given extended data and custom initialization block. */
@PublishedApi
internal inline fun <reified T> Context.intentOf(
    vararg pairs: Pair<String, Any?>,
    noinline init: (Intent.() -> Unit)? = null
): Intent {
    val intent = Intent(this, T::class.java)
    intent.putExtras(bundleOf(*pairs))
    init?.invoke(intent)
    return intent
}