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

package androidx.support.v7.app

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity


/**
 * Add a fragment to the activity state.
 * @param fragment The fragment to be added.  This fragment must not already
 * be added to the activity.
 * @param containerViewId Optional identifier of the container this fragment is
 * to be placed in.  If 0, it will not be placed in a container.
 * @param tag Optional tag name for the fragment, to later retrieve the
 * fragment with FragmentManager.findFragmentByTag(String)}.
 * @param runnable Runnable that will be run after this transaction has
 * been committed
 */
@JvmOverloads
fun AppCompatActivity.addFragment(
    fragment: Fragment,
    @IdRes containerViewId: Int = 0,
    tag: String? = null,
    runnable: Runnable? = null
) {
    val transaction =
        supportFragmentManager.beginTransaction().add(containerViewId, fragment, tag)
    runnable.let { transaction.runOnCommit(runnable) }
    transaction.commit();
}

/**
 * Replace an existing fragment that was added to a container.
 * @param fragment The fragment to be added.  This fragment must not already
 * be added to the activity.
 * @param containerViewId Optional identifier of the container this fragment is
 * to be placed in.  If 0, it will not be placed in a container.
 * @param tag Optional tag name for the fragment, to later retrieve the
 * fragment with FragmentManager.findFragmentByTag(String)}.
 * @param runnable Runnable that will be run after this transaction has
 * been committed
 */
@JvmOverloads
fun AppCompatActivity.replaceFragment(
    fragment: Fragment,
    @IdRes containerViewId: Int = 0,
    tag: String? = null,
    runnable: Runnable? = null
) {
    val transaction =
        supportFragmentManager.beginTransaction().replace(containerViewId, fragment, tag)
    runnable.let { transaction.runOnCommit(runnable) }
    transaction.commit();
}

/**
 * Remove an existing fragment.  If it was added to a container, its view
 * is also removed from that container.
 *
 * @param fragment The fragment to be removed.
 *
 * @param runnable Runnable that will be run after this transaction has
 * been committed
 */
@JvmOverloads
fun AppCompatActivity.removeFragment(
    fragment: Fragment,
    runnable: Runnable? = null
) {
    val transaction = supportFragmentManager.beginTransaction().remove(fragment)
    runnable.let { transaction.runOnCommit(runnable) }
    transaction.commit();
}

/**
 * Hides an existing fragment.  This is only relevant for fragments whose
 * views have been added to a container, as this will cause the view to
 * be hidden.
 *
 * @param fragment The fragment to be hidden.
 * @param runnable Runnable that will be run after this transaction has
 * been committed
 */
@JvmOverloads
fun AppCompatActivity.hideFragment(
    fragment: Fragment,
    runnable: Runnable? = null
) {
    val transaction = supportFragmentManager.beginTransaction().hide(fragment)
    runnable.let { transaction.runOnCommit(runnable) }
    transaction.commit()
}

/**
 * Shows a previously hidden fragment.  This is only relevant for fragments whose
 * views have been added to a container, as this will cause the view to
 * be shown.
 *
 * @param fragment The fragment to be shown.
 * @param runnable Runnable that will be run after this transaction has
 * been committed
 */
@JvmOverloads
fun AppCompatActivity.showFragment(
    fragment: Fragment,
    runnable: Runnable? = null
) {
    val transaction = supportFragmentManager.beginTransaction().show(fragment)
    runnable.let { transaction.runOnCommit(runnable) }
    transaction.commit()
}

/**
 * Detach the given fragment from the UI.  This is the same state as
 * when it is put on the back stack: the fragment is removed from
 * the UI, however its state is still being actively managed by the
 * fragment manager.  When going into this state its view hierarchy
 * is destroyed.
 *
 * @param fragment The fragment to be detached.
 * @param runnable Runnable that will be run after this transaction has
 * been committed
 */
@JvmOverloads
fun AppCompatActivity.detachFragment(
    fragment: Fragment,
    runnable: Runnable? = null
) {
    val transaction = supportFragmentManager.beginTransaction().detach(fragment)
    runnable.let { transaction.runOnCommit(runnable) }
    transaction.commit()
}

/**
 * Re-attach a fragment after it had previously been detached from
 * the UI.  This causes its view hierarchy to be re-created, attached to the UI,
 * and displayed.
 *
 * @param fragment The fragment to be attached.
 * @param runnable Runnable that will be run after this transaction has
 * been committed
 */
@JvmOverloads
fun AppCompatActivity.attachFragment(
    fragment: Fragment,
    runnable: Runnable? = null
) {
    val transaction = supportFragmentManager.beginTransaction().attach(fragment)
    runnable.let { transaction.runOnCommit(runnable) }
    transaction.commit()
}
