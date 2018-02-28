Android KTX
===========

A set of Kotlin extensions for Android app development. The goal of Android KTX is to make Android
development with Kotlin more concise, pleasant, and idiomatic. It is an explicit goal of this
project to not add any new feature to the existing Android APIs.

**Kotlin:**
```kotlin
val uri = Uri.parse(myUriString)
```
**Kotlin with Android KTX:**
```kotlin
val uri = myUriString.toUri()
```

----

**Kotlin:**
```kotlin
sharedPreferences.edit()
    .putBoolean("key", value)
    .apply()
```
**Kotlin with Android KTX:**
```kotlin
sharedPreferences.edit {
    putBoolean("key", value)
}
```

----

**Kotlin:**
```kotlin
val pathDifference = Path(myPath1).apply {
    op(myPath2, Path.Op.DIFFERENCE)
}

canvas.apply {
  val checkpoint = save()
  translate(0F, 100F)
  drawPath(pathDifference, myPaint)
  restoreToCount(checkpoint)
}
```
**Kotlin with Android KTX:**
```kotlin
val pathDifference = myPath1 - myPath2

canvas.withTranslation(y = 100F) {
    drawPath(pathDifference, myPaint)
}
```

----

**Kotlin:**
```kotlin
view.viewTreeObserver.addOnPreDrawListener(
    object : ViewTreeObserver.OnPreDrawListener {
        override fun onPreDraw(): Boolean {
            viewTreeObserver.removeOnPreDrawListener(this)
            actionToBeTriggered()
            return true
        }
    })
```
**Kotlin with Android KTX:**
```kotlin
view.doOnPreDraw {
     actionToBeTriggered()
}
```


Getting Started
---------------

To add Android KTX to your project, add the following to your app module's `build.gradle`:

```groovy
repositories {
    google()
}

dependencies {
    implementation 'androidx.core:core-ktx:0.2'
}
```

Then, in your Kotlin files where you'd like to use the extensions, import the appropriate packages.


Project Status
--------------

This project is currently in **preview**, and we are seeking feedback from the community. We
encourage you to try Android KTX so you can suggest ideas and report issues. Please see the
["How to Contribute" section](#how-to-contribute) if you would like to contribute.

During the preview period, the Android KTX APIs may change at anytime. When the project reaches a
stable state, we will update it to version 1.0 (or later). From that point forward, we will be much
more rigorous and careful about maintaining API compatibility.

This project does not currently cover the Android Support Library or Architecture Components APIs.


Links
-----

 * [API reference documentation](https://android.github.io/android-ktx/core-ktx/)
 * [Issue tracker: Report a defect or feature request](https://github.com/android/android-ktx/issues/new)
 * [StackOverflow: Ask "how-to" and "why didn't it work" type questions](https://stackoverflow.com/questions/ask?tags=android-ktx)


How to Contribute
-----------------

We welcome your contributions to this project. There are various ways to contribute:

**Reporting issues**

Help improve the project by reporting issues that you find by filing a new issue at the
[Android KTX issue tracker](https://github.com/android/android-ktx/issues/new).

**Features suggestions**

You can also add feature suggestions by filing a new issue at the
[Android KTX issue tracker](https://github.com/android/android-ktx/issues/new).

**Documentation**

You can help by adding or improving existing documentation. Simply send us a pull request for us to
consider your proposed changes.

**Bug fixes**

Pull requests are welcome for minor bug fixes that do not involve any changes to existing API.
These changes should ideally be accompanied by a test case that would have otherwise failed without
the fix.

**New API or API changes**

Pull requests for new APIs or changes to existing APIs are welcome, but may require a bit of
discussion. Consider creating an issue to discuss any changes before you implement the change.

Before submitting,

 * If you are making an API change, run `./gradlew updateApi` and commit any changes in the `api/`
   directory.
 * Check that lint, unit tests, and code style enforcement all pass by running `./gradlew check`.
 * Check that instrumentation tests pass by starting an API 26 or newer emulator and running
   `./gradlew connectedCheck`

_Note: First-time contributors to a Google or Android project will be required to sign a CLA._


License
=======

    Copyright 2018 The Android Open Source Project

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
