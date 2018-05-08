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

package androidx.net

import org.json.JSONObject

/**
<<<<<<< HEAD
 * Check JSONObject value before putting it
=======
 * Check JsonObject value before putting it
>>>>>>> 938e367... Add JsonObject checking value
 *
 * @see JSONObject.put
 */
fun JSONObject.checkNotBlank(
    key: String,
    value: Any?
): JSONObject = when (value) {
    is String -> if (value.isNotBlank()) put(key, value) else this
    is Int -> if (value != 0) put(key, value) else this
    is Long -> if (value != 0L) put(key, value) else this
    is Double -> if (value != 0.0) put(key, value) else this
    is Float -> if (value != 0f) put(key, value) else this
    else -> this
}
