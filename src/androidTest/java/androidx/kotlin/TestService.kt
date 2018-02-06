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

package androidx.kotlin

import android.app.Service
import android.content.Intent
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicReference

class TestService : Service() {

    override fun onCreate() {
        super.onCreate()
        isRunning.set(true)
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        startIntent.set(intent)
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent) = Binder(intent)

    override fun onDestroy() {
        super.onDestroy()
        isRunning.set(false)
        startIntent.set(null)
    }

    companion object {

        val startIntent = AtomicReference<Intent?>(null)
        val isRunning = AtomicBoolean()
    }

    class Binder(
        val intent: Intent
    ) : android.os.Binder()
}