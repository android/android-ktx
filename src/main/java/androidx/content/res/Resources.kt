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

package androidx.content.res

import android.content.res.Resources
import android.os.Build
import android.support.annotation.StringRes
import android.text.Html
import android.text.TextUtils

/**
 * Returns the string value associated with the [id], substituting the format arguments.
 * HTML styling information will be preserved and applied.
 *
 * @param formatArgs Format arguments, strings will be automatically escaped
 */
fun Resources.getText(@StringRes id: Int, vararg formatArgs: Any): CharSequence {
    val args = formatArgs.map { if (it is String) TextUtils.htmlEncode(it) else it }.toTypedArray()
    val string = getString(id, *args)
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(string, Html.FROM_HTML_MODE_LEGACY)
    } else {
        @Suppress("DEPRECATION")
        Html.fromHtml(string)
    }
}
