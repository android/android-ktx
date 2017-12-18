/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.animation

import android.animation.Animator
import android.support.annotation.RequiresApi

/**
 * Add an action which will be invoked when the animation has ended.
 *
 * @see Animator.end
 */
fun Animator.doOnEnd(action: (Animator) -> Unit) {
    addListener(onEnd = action)
}

/**
 * Add an action which will be invoked when the animation has started.
 *
 * @see Animator.start
 */
fun Animator.doOnStart(action: (Animator) -> Unit) {
    addListener(onStart = action)
}

/**
 * Add an action which will be invoked when the animation has been cancelled.
 *
 * @see Animator.cancel
 */
fun Animator.doOnCancel(action: (Animator) -> Unit) {
    addListener(onCancel = action)
}

/**
 * Add an action which will be invoked when the animation has repeated.
 */
fun Animator.doOnRepeat(action: (Animator) -> Unit) {
    addListener(onRepeat = action)
}

/**
 * Add an action which will be invoked when the animation has resumed after a pause.
 *
 * @see Animator.resume
 */
@RequiresApi(19)
fun Animator.doOnResume(action: (Animator) -> Unit) {
    addPauseListener(onResume = action)
}

/**
 * Add an action which will be invoked when the animation has been paused.
 *
 * @see Animator.pause
 */
@RequiresApi(19)
fun Animator.doOnPause(action: (Animator) -> Unit) {
    addPauseListener(onPause = action)
}

/**
 * Add a listener to this Animator using the provided actions.
 */
fun Animator.addListener(
    onEnd: ((Animator) -> Unit)? = null,
    onStart: ((Animator) -> Unit)? = null,
    onCancel: ((Animator) -> Unit)? = null,
    onRepeat: ((Animator) -> Unit)? = null) {
    if (onEnd == null && onStart == null && onCancel == null && onRepeat == null) return

    addListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(animator: Animator) {
            onRepeat?.invoke(animator)
        }

        override fun onAnimationEnd(animator: Animator) {
            onEnd?.invoke(animator)
        }

        override fun onAnimationCancel(animator: Animator) {
            onCancel?.invoke(animator)
        }

        override fun onAnimationStart(animator: Animator) {
            onStart?.invoke(animator)
        }
    })
}

/**
 * Add a pause and resume listener to this Animator using the provided actions.
 */
@RequiresApi(19)
fun Animator.addPauseListener(
    onResume: ((Animator) -> Unit)? = null,
    onPause: ((Animator) -> Unit)? = null) {
    if (onResume == null && onPause == null) return

    addPauseListener(object : Animator.AnimatorPauseListener {
        override fun onAnimationPause(animator: Animator) {
            onPause?.invoke(animator)
        }

        override fun onAnimationResume(animator: Animator) {
            onResume?.invoke(animator)
        }
    })
}