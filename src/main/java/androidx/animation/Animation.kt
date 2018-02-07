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

package androidx.animation

import android.view.animation.Animation

/**
 * Add an action which will be invoked when the animation has ended.
 *
 * @return the [Animation.AnimationListener] added to the Animation
 * @see Animation.end
 */
fun Animation.doOnEnd(action: (animation: Animation) -> Unit) = addListener(onEnd = action)

/**
 * Add an action which will be invoked when the animation has started.
 *
 * @return the [Animation.AnimationListener] added to the Animation
 * @see Animation.start
 */
fun Animation.doOnStart(action: (animation: Animation) -> Unit) = addListener(onStart = action)


/**
 * Add an action which will be invoked when the animation has repeated.
 * @return the [Animation.AnimationListener] added to the Animation
 */
fun Animation.doOnRepeat(action: (animation: Animation) -> Unit) = addListener(onRepeat = action)

/**
 * Add a listener to this Animation using the provided actions.
 */
fun Animation.addListener(
    onEnd: ((animation: Animation) -> Unit)? = null,
    onStart: ((animation: Animation) -> Unit)? = null,
    onRepeat: ((animation: Animation) -> Unit)? = null
): Animation.AnimationListener {
    val listener = object : Animation.AnimationListener {
        override fun onAnimationRepeat(animation: Animation) {
            onRepeat?.invoke(animation)
        }

        override fun onAnimationEnd(animation: Animation) {
            onEnd?.invoke(animation)
        }

        override fun onAnimationStart(animation: Animation) {
            onStart?.invoke(animation)
        }
    }
    setAnimationListener(listener)
    return listener
}

