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

package androidx.core.animation

import android.graphics.drawable.Animatable2
import android.graphics.drawable.Drawable
import android.support.annotation.RequiresApi

@RequiresApi(23)
fun Animatable2.doOnStart(action: (Drawable?) -> Unit) = addCallback(onStart = action)

@RequiresApi(23)
fun Animatable2.doOnEnd(action: (Drawable?) -> Unit) = addCallback(onEnd = action)

/**
 * Add a callback to this Animatable2 using the provided actions.
 */
@RequiresApi(23)
fun Animatable2.addCallback(
    onStart: ((drawable: Drawable?) -> Unit)? = null,
    onEnd: ((drawable: Drawable?) -> Unit)? = null
): Animatable2.AnimationCallback {
    val callback = object : Animatable2.AnimationCallback() {
        override fun onAnimationStart(drawable: Drawable?) {
            onStart?.invoke(drawable)
        }

        override fun onAnimationEnd(drawable: Drawable?) {
            onEnd?.invoke(drawable)
        }
    }
    registerAnimationCallback(callback)
    return callback
}