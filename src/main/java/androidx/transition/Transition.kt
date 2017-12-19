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

package androidx.transition

import android.support.annotation.RequiresApi
import android.transition.Transition

/**
 * Add an action which will be invoked when this transition has ended.
 */
@RequiresApi(19)
fun Transition.doOnEnd(action: (Transition) -> Unit) {
    addListener(onEnd = action)
}

/**
 * Add an action which will be invoked when this transition has started.
 */
@RequiresApi(19)
fun Transition.doOnStart(action: (Transition) -> Unit) {
    addListener(onStart = action)
}

/**
 * Add an action which will be invoked when this transition has been cancelled.
 */
@RequiresApi(19)
fun Transition.doOnCancel(action: (Transition) -> Unit) {
    addListener(onCancel = action)
}

/**
 * Add an action which will be invoked when this transition has resumed after a pause.
 */
@RequiresApi(19)
fun Transition.doOnResume(action: (Transition) -> Unit) {
    addListener(onResume = action)
}

/**
 * Add an action which will be invoked when this transition has been paused.
 */
@RequiresApi(19)
fun Transition.doOnPause(action: (Transition) -> Unit) {
    addListener(onPause = action)
}

/**
 * Add a listener to this Transition using the provided actions.
 */
@RequiresApi(19)
fun Transition.addListener(
        onEnd: ((Transition) -> Unit)? = null,
        onStart: ((Transition) -> Unit)? = null,
        onCancel: ((Transition) -> Unit)? = null,
        onResume: ((Transition) -> Unit)? = null,
        onPause: ((Transition) -> Unit)? = null) {
    addListener(object : Transition.TransitionListener {
        override fun onTransitionEnd(transition: Transition) {
            onEnd?.invoke(transition)
        }

        override fun onTransitionResume(transition: Transition) {
            onResume?.invoke(transition)
        }

        override fun onTransitionPause(transition: Transition) {
            onPause?.invoke(transition)
        }

        override fun onTransitionCancel(transition: Transition) {
            onCancel?.invoke(transition)
        }

        override fun onTransitionStart(transition: Transition) {
            onStart?.invoke(transition)
        }
    })
}