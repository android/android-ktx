package androidx.app

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.annotation.RequiresApi

/**
 * Syntactic sugar for [Activity.startActivityForResult(Intent, Int)][Activity.startActivityForResult].
 */
inline fun <reified T: Activity> Activity.startActivityForResult(code: Int, block: Intent.() -> Unit = {}) =
        startActivityForResult(Intent(this, T::class.java).apply(block), code)

/**
 * Syntactic sugar for [Activity.startActivityForResult(Intent, Int, Bundle)][Activity.startActivityForResult].
 */
@RequiresApi(16)
inline fun <reified T: Activity> Activity.startActivityForResult(code: Int, options: Bundle, block: Intent.() -> Unit = {}) =
        startActivityForResult(Intent(this, T::class.java).apply(block), code, options)