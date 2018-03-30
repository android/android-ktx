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

import android.animation.LayoutTransition
import android.animation.LayoutTransition.TransitionListener
import android.view.View
import android.view.ViewGroup

fun LayoutTransition.doOnStart(action: (LayoutTransition, ViewGroup, View, Int) -> Unit) =
    addListener(onStart = action)

fun LayoutTransition.doOnEnd(action: (LayoutTransition, ViewGroup, View, Int) -> Unit) =
    addListener(onEnd = action)


fun LayoutTransition.addListener(
    onStart: ((LayoutTransition, ViewGroup, View, Int) -> Unit)? = null,
    onEnd: ((LayoutTransition, ViewGroup, View, Int) -> Unit)? = null
) {
    addTransitionListener(
            object : TransitionListener {
                override fun startTransition(
                    transition: LayoutTransition,
                    container: ViewGroup,
                    view: View,
                    transitionType: Int
                ) {
                    onStart?.invoke(transition, container, view, transitionType)
                }

                override fun endTransition(
                    transition: LayoutTransition,
                    container: ViewGroup,
                    view: View,
                    transitionType: Int
                ) {
                    onEnd?.invoke(transition, container, view, transitionType)
                }

            }
    )
}