package androidx.util

import android.util.LruCache

private class DelegateLruCache<K, V>(
        maxSize: Int,
        private val sizeFunc: (K, V) -> Int,
        private val factory: (K) -> V?,
        val onEntryRemoved: ((Boolean, K, V, V?) -> Unit)) : LruCache<K, V>(maxSize) {

    override fun sizeOf(key: K, value: V): Int = sizeFunc(key, value)
    override fun create(key: K): V? = factory(key)
    override fun entryRemoved(evicted: Boolean, key: K, oldValue: V, newValue: V) {
        onEntryRemoved(evicted, key, oldValue, newValue)
    }
}

/**
 * Creates [android.util.LruCache] with the given parameters.
 *
 * @param maxSize for caches that do not specify [sizeFunc], this is
 * the maximum number of entries in the cache. For all other caches,
 * this is the maximum sum of the sizes of the entries in this cache.
 * @param sizeFunc function that returns the size of the entry for key and value in
 * user-defined units. The default implementation returns 1.
 * @param factory a factory called after a cache miss to compute a value for the corresponding key.
 * Returns the computed value or null if no value can be computed. The default implementation
 * returns null.
 * @param onEntryRemoved a function called for entries that have been evicted or removed.
 *
 * @see LruCache.sizeOf
 * @see LruCache.create
 * @see LruCache.entryRemoved
 */
fun <K, V> lruCache(maxSize: Int,
                    sizeFunc: (K, V) -> Int = { _, _ -> 1 },
                    factory: (K) -> V? = { _ -> null },
                    onEntryRemoved: ((Boolean, K, V, V?) -> Unit) = { _, _, _, _ -> }):
        LruCache<K, V> {
    return DelegateLruCache(maxSize, sizeFunc, factory, onEntryRemoved)
}
