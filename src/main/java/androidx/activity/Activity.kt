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

package androidx.activity

/**
 * A short way to show a toast
 */
fun Activity.toastShort(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Activity.toastLong(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

/**
 * Add fragment to back stack
 */
fun Activity.addFragment(fragment: Class<Fragment>, tag: String? = null, arguments: Bundle? = null) {
    val fragmentInstance: Fragment = fragment.newInstance()
    if (arguments != null) {
        fragmentInstance.arguments = arguments
    }
    fragmentManager.beginTransaction().add(fragmentInstance, tag).commit()
}

fun Activity.addFragment(containerId: Int, fragment: Class<Fragment>, tag: String? = null, arguments: Bundle? = null) {
    val fragmentInstance: Fragment = fragment.newInstance()
    if (arguments != null) {
        fragmentInstance.arguments = arguments
    }
    fragmentManager.beginTransaction().add(containerId, fragmentInstance, tag).commit()
}

fun Activity.replaceFragment(containerId: Int, fragment: Class<Fragment>, tag: String? = null, arguments: Bundle? = null) {
    val fragmentInstance: Fragment = fragment.newInstance()
    if (arguments != null) {
        fragmentInstance.arguments = arguments
    }
    fragmentManager.beginTransaction().replace(containerId, fragmentInstance, tag).commit()
}