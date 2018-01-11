package androidx.content

import android.content.Context
import android.content.res.TypedArray
import android.support.annotation.AttrRes
import android.support.annotation.RequiresApi
import android.support.annotation.StyleRes
import android.util.AttributeSet

/**
 * Return the handle to a system-level service by class.
 *
 * The return type of this function intentionally uses a
 * [platform type](https://kotlinlang.org/docs/reference/java-interop.html#null-safety-and-platform-types)
 * to allow callers to decide whether they require a service be present or can tolerate its absence.
 *
 * @see Context.getSystemService(Class)
 */
@RequiresApi(23)
@Suppress("HasPlatformType") // Intentionally propagating platform type with unknown nullability.
inline fun <reified T> Context.systemService() = getSystemService(T::class.java)

/**
 * Executes [block] on a [TypedArray] receiver. The [TypedArray] holds the attribute
 * values in [set] that are listed in [attrs]. In addition, if the given [AttributeSet]
 * specifies a style class (through the `style` attribute), that style will be applied
 * on top of the base attributes it defines.
 *
 * @param set The base set of attribute values.
 * @param attrs	The desired attributes to be retrieved. These attribute IDs must be
 *              sorted in ascending order.
 * @param defStyleAttr An attribute in the current theme that contains a reference to
 *                     a style resource that supplies defaults values for the [TypedArray].
 *                     Can be 0 to not look for defaults.
 * @param defStyleRes A resource identifier of a style resource that supplies default values
 *                    for the [TypedArray], used only if [defStyleAttr] is 0 or can not be found
 *                     in the theme. Can be 0 to not look for defaults.
 *
 * @see Context.obtainStyledAttributes
 * @see android.content.res.Resources.Theme.obtainStyledAttributes
 */
inline fun Context.withStyledAttributes(
        set: AttributeSet? = null,
        attrs: IntArray,
        @AttrRes defStyleAttr: Int = 0,
        @StyleRes defStyleRes: Int = 0,
        block: TypedArray.() -> Unit) {
    val typedArray = obtainStyledAttributes(set, attrs, defStyleAttr, defStyleRes)
    try {
        typedArray.block()
    } finally {
        typedArray.recycle()
    }
}

/**
 * Executes [block] on a [TypedArray] receiver. The [TypedArray] holds the the values
 * defined by the style resource [resourceId] which are listed in [attrs].
 *
 * @param attrs	The desired attributes. These attribute IDs must be sorted in ascending order.
 *
 * @see Context.obtainStyledAttributes
 * @see android.content.res.Resources.Theme.obtainStyledAttributes
 */
inline fun Context.withStyledAttributes(
        @StyleRes resourceId: Int = 0,
        attrs: IntArray,
        block: TypedArray.() -> Unit) {
    val typedArray = obtainStyledAttributes(resourceId, attrs)
    try {
        typedArray.block()
    } finally {
        typedArray.recycle()
    }
}
