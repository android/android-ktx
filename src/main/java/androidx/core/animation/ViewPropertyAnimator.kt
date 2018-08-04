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

package androidx.core.animation

import android.animation.Animator
import android.animation.ValueAnimator
import android.view.ViewPropertyAnimator
import androidx.annotation.RequiresApi

/**
 * Add an action which will be invoked when the animation has ended.
 *
 * @return the [Animator.AnimatorListener] added to the Animator
 * @see Animator.end
 */
fun ViewPropertyAnimator.doOnEnd(action: (animator: Animator) -> Unit) =
        setListener(onEnd = action)

/**
 * Add an action which will be invoked when the animation has started.
 *
 * @return the [Animator.AnimatorListener] added to the Animator
 * @see Animator.start
 */
fun ViewPropertyAnimator.doOnStart(action: (animator: Animator) -> Unit) =
        setListener(onStart = action)

/**
 * Add an action which will be invoked when the animation has been cancelled.
 *
 * @return the [Animator.AnimatorListener] added to the Animator
 * @see Animator.cancel
 */
fun ViewPropertyAnimator.doOnCancel(action: (animator: Animator) -> Unit) =
        setListener(onCancel = action)

/**
 * Add an action which will be invoked when the animation has repeated.
 * @return the [Animator.AnimatorListener] added to the Animator
 */
fun ViewPropertyAnimator.doOnRepeat(action: (animator: Animator) -> Unit) =
        setListener(onRepeat = action)

/**
 * Add an action which will be invoked when the animation has been updated.
 *
 * @return the [ValueAnimator.AnimatorUpdateListener] added to the Animator
 * @see Animator.pause
 */
@RequiresApi(19)
fun ViewPropertyAnimator.doOnUpdate(action: (animator: ValueAnimator?) -> Unit) =
        setUpdateListener(onUpdate = action)

/**
 * Add a listener to this Animator using the provided actions.
 */
fun ViewPropertyAnimator.setListener(
    onEnd: ((animator: Animator) -> Unit)? = null,
    onStart: ((animator: Animator) -> Unit)? = null,
    onCancel: ((animator: Animator) -> Unit)? = null,
    onRepeat: ((animator: Animator) -> Unit)? = null
): Animator.AnimatorListener {
    val listener = object : Animator.AnimatorListener {
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
    }
    setListener(listener)
    return listener
}

/**
 * Add an update listener to this Animator using the provided actions.
 */
@RequiresApi(19)
fun ViewPropertyAnimator.setUpdateListener(
    onUpdate: ((animator: ValueAnimator?) -> Unit)? = null
): ValueAnimator.AnimatorUpdateListener {
    val listener = ValueAnimator.AnimatorUpdateListener {
        onUpdate?.invoke(it)
    }
    setUpdateListener(listener)
    return listener
}
