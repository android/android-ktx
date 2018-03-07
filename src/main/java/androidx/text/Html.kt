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

package androidx.text

import android.annotation.SuppressLint
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.N
import android.text.Html
import android.text.Html.FROM_HTML_MODE_LEGACY
import android.text.Html.ImageGetter
import android.text.Html.TagHandler
import android.text.Spanned

/**
 * Returns a new [Spanned] from [CharSequence] HTML.
 *
 * In Android 7.0, additional options were introduced to allow for more
 * control on activity launch animations.
 *
 * @param flags Additional option to set the behavior of the HTML parsing. Default is set to
 * [Html.FROM_HTML_MODE_LEGACY] which was introduced in Android 7.0.
 */
fun CharSequence.parseAsHtml(
    @SuppressLint("InlinedApi") flags: Int = FROM_HTML_MODE_LEGACY
): Spanned {
    return if (SDK_INT >= N) {
        Html.fromHtml(toString(), flags)
    } else {
        @Suppress("DEPRECATION")
        Html.fromHtml(toString())
    }
}

/**
 * Returns a new [Spanned] from [CharSequence] HTML.
 *
 * In Android 7.0, additional options were introduced to allow for more
 * control on activity launch animations.
 *
 * @param flags Additional option to set the behavior of the HTML parsing. Default is set to
 * [Html.FROM_HTML_MODE_LEGACY] which was introduced in Android 7.0.
 * @param imageGetter Returns displayable styled text from the provided HTML string.
 * @param tagHandler Notified when HTML tags are encountered a tag the parser does
 * not know how to interpret.
 */
fun CharSequence.parseAsHtml(
    @SuppressLint("InlinedApi") flags: Int = FROM_HTML_MODE_LEGACY,
    imageGetter: ImageGetter,
    tagHandler: TagHandler
): Spanned {
    return if (SDK_INT >= N) {
        Html.fromHtml(toString(), flags, imageGetter, tagHandler)
    } else {
        @Suppress("DEPRECATION")
        Html.fromHtml(toString(), imageGetter, tagHandler)
    }
}
