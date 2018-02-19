package androidx.util

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class LruCacheTest {
    private data class TestData(var x: String = "bla")

    @Test
    fun testSize() {
        val cache = lruCache<String, TestData>(200, { k, (x) -> k.length * x.length })
        cache.put("long", TestData())
        assertEquals(cache.size(), 12)
    }

    @Test
    fun testFactory() {
        val cache = lruCache<String, TestData>(200, factory = { key -> TestData("$key foo") })
        assertEquals(cache.get("kung"), TestData("kung foo"))
    }

    @Test
    fun testOnEntryRemoved() {
        var wasCalled = false

        val cache = lruCache<String, TestData>(200, onEntryRemoved = { _, _, _, _ ->
            wasCalled = true
        })
        val initial = TestData()
        cache.put("a", initial)
        cache.remove("a")
        assertTrue(wasCalled)
    }
}