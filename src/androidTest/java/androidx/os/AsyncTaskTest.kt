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

package androidx.os

import android.os.AsyncTask
import org.junit.Assert
import org.junit.Test

/**
 * Created by muhannad on 2/16/18.
 */
class AsyncTaskTest {

    @Test
    fun testtask() {
        println("main , Thread : ${Thread.currentThread().name}")
        val execute = AsyncTaskKTX<Int, Int, Int>().runInBackground {
            doInBg {
                println("doInBg function in test , Thread : ${Thread.currentThread().name}")
                Assert.assertEquals(4, it.size)
                for (i in 1..5) {
                    Thread.sleep(1000)
                    publishProgress(i)
                }
                it.size
            }
            onUpdate {
                println("onUpdate(${it[0]}) in test , Thread : ${Thread.currentThread().name}")
            }
        }.execute(3, 4, 5, 6)

        while (execute.status != AsyncTask.Status.FINISHED) { }
        Assert.assertEquals(AsyncTask.Status.FINISHED, execute.status)
    } }