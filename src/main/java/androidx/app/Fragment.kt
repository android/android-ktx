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

import android.app.Activity
import android.app.Fragment
import android.content.Intent
import android.os.Bundle
import android.support.annotation.RequiresApi

/**
 * Syntactic sugar for [Fragment.startActivityForResult(Intent, Int)][Fragment.startActivityForResult].
 *
 * The context used is this fragment's Activity, therefore this method will throw an exception if
 * the fragment isn't attached.
 */
inline fun <reified T: Activity> Fragment.startActivityForResult(code: Int, block: Intent.() -> Unit = {}) =
    startActivityForResult(Intent(activity, T::class.java).apply(block), code)

/**
 * Syntactic sugar for [Fragment.startActivityForResult(Intent, Int, Bundle)][Fragment.startActivityForResult].
 *
 * The context used is this fragment's Activity, therefore this method will throw an exception if
 * the fragment isn't attached.
 */
@RequiresApi(16)
inline fun <reified T: Activity> Fragment.startActivityForResult(code: Int, options: Bundle, block: Intent.() -> Unit = {}) =
    startActivityForResult(Intent(activity, T::class.java).apply(block), code, options)