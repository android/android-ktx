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

import android.app.FragmentManager
import android.app.FragmentTransaction

/**
 * Allows performing several Fragment operations within a FragmentTransaction
 * with a call to [FragmentTransaction.commit] to schedule a commit
 * for the provided Fragment operations.
 *
 * ```
 * fragmentManager.transact {
 *     remove(fragmentOne)
 *     add(R.id.fragment_container, fragmentTwo)
 * }
 * ```
 */
inline fun FragmentManager.transact(actions: FragmentTransaction.() -> Unit) {
    val transaction = beginTransaction()
    actions(transaction)
    transaction.commit()
}