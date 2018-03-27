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

package androidx.core.os

import android.content.Intent
import android.content.pm.PackageManager

/**
 * Calls onSafe block, if intent invocation is safe (if intent can be processed by whatever application), else calls onError block
 **/
inline fun Intent.safe(
    packageManager: PackageManager,
    onSafe: (Intent) -> Unit,
    onError: () -> Unit
) {
    val activities = packageManager.queryIntentActivities(this, 0)
    if ((activities?.size ?: 0) > 0) onSafe.invoke(this) else onError.invoke()
}
