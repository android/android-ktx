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

package androidx.core.database.sqlite

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.support.test.InstrumentationRegistry
import androidx.testutils.assertThrows
import org.junit.Assert.assertEquals
import org.junit.Test

class SQLiteDatabaseTest {
    private val context = InstrumentationRegistry.getContext()
    private val openHelper = object : SQLiteOpenHelper(context, null, null, 1) {
        override fun onCreate(db: SQLiteDatabase) {
            db.execSQL("CREATE TABLE test(name TEXT)")
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        }
    }
    private val db = openHelper.writableDatabase

    @Test fun throwingBodyNotSuccessful() {
        val exception = RuntimeException()
        assertThrows<RuntimeException> {
            db.transaction {
                insert("test", null, ContentValues().apply { put("name", "Alice") })
                throw exception
            }
        }.isSameAs(exception)

        val query = db.rawQuery("SELECT COUNT(*) FROM test", emptyArray())
        query.moveToFirst()
        assertEquals(0L, query.getLong(0))
        query.close()
    }

    @Test fun bodyReturnValue() {
        val result = db.transaction {
            "Hey"
        }
        assertEquals("Hey", result)
    }
}
