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

package androidx.core.view

import android.animation.Animator
import android.view.ViewPropertyAnimator

/**
 * Add an action which will be invoked when the animation has ended.
 *
 * @return the [ViewPropertyAnimator] receiver.
 * @see Animator.end
 */
fun ViewPropertyAnimator.doOnEnd(action: (animator: Animator) -> Unit): ViewPropertyAnimator {
    setListener(onEnd = action)
    return this
}

/**
 * Add an action which will be invoked when the animation has started.
 *
 * @return the [ViewPropertyAnimator] receiver.
 * @see Animator.start
 */
fun ViewPropertyAnimator.doOnStart(action: (animator: Animator) -> Unit): ViewPropertyAnimator {
    setListener(onStart = action)
    return this
}

/**
 * Add an action which will be invoked when the animation has been cancelled.
 *
 * @return the [ViewPropertyAnimator] receiver.
 * @see Animator.cancel
 */
fun ViewPropertyAnimator.doOnCancel(action: (animator: Animator) -> Unit): ViewPropertyAnimator {
    setListener(onCancel = action)
    return this
}

/**
 * Add an action which will be invoked when the animation has repeated.
 * @return the [ViewPropertyAnimator] receiver.
 */
fun ViewPropertyAnimator.doOnRepeat(action: (animator: Animator) -> Unit): ViewPropertyAnimator {
    setListener(onRepeat = action)
    return this
}

/**
 * Sets the listener to this Animator using the provided actions.
 * @return the [ViewPropertyAnimator] receiver.
 */
fun ViewPropertyAnimator.setListener(
        onEnd: ((animator: Animator) -> Unit)? = null,
        onStart: ((animator: Animator) -> Unit)? = null,
        onRepeat: ((animator: Animator) -> Unit)? = null,
        onCancel: ((animator: Animator) -> Unit)? = null
): ViewPropertyAnimator {
    val listener = object : Animator.AnimatorListener {
        override fun onAnimationStart(animator: Animator) {
            onStart?.invoke(animator)
        }

        override fun onAnimationRepeat(animator: Animator) {
            onRepeat?.invoke(animator)
        }

        override fun onAnimationEnd(animator: Animator) {
            onEnd?.invoke(animator)
        }

        override fun onAnimationCancel(animator: Animator) {
            onCancel?.invoke(animator)
        }
    }
    setListener(listener)
    return this
}
