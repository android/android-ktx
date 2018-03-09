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

package androidx.app

import android.app.Fragment
import android.content.Intent
import androidx.content.intentOf

/**
 * Launch a new activity [T].
 *
 * @param pairs extended data to the intent
 * @param init custom intent initialization block
 */
inline fun <reified T> Fragment.startActivity(
    vararg pairs: Pair<String, Any?>,
    noinline init: (Intent.() -> Unit)? = null
) = startActivity(activity.intentOf<T>(*pairs, init = init))

/**
 * Launch a new activity [T] for which you would like a result when it finished.
 *
 * @param requestCode code that will be returned in [Fragment.onActivityResult].
 * @param pairs extended data to the intent
 * @param init custom intent initialization block
 */
inline fun <reified T> Fragment.startActivityForResult(
    requestCode: Int,
    vararg pairs: Pair<String, Any?>,
    noinline init: (Intent.() -> Unit)? = null
) = startActivityForResult(activity.intentOf<T>(*pairs, init = init), requestCode)