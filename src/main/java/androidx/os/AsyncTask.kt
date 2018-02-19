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
/**
 * Created by muhannad on 2/13/18.
 */

class AsyncTaskKTX<Params, Progress, Result> : AsyncTask<Params, Progress, Result>() {
    private lateinit var backgroundAction: (Array<out Params>) -> Result
    private var updateAction: (Array<out Progress>) -> Unit = {}
    private var postExcuteAction: (Result) -> Unit = {}

    override fun doInBackground(vararg params: Params): Result {
        return backgroundAction(params)
    }

    override fun onProgressUpdate(vararg values: Progress) {
        super.onProgressUpdate(*values)
        updateAction(values)
    }

    override fun onPostExecute(result: Result) {
        super.onPostExecute(result)
        postExcuteAction(result)
    }

    fun runInBackground(init: Builder.() -> Unit): AsyncTask<Params, Progress, Result> {
        val build = Builder()
        build.init()
        return this
    }

    inner class Builder {

        fun onUpdate(update: (Array<out Progress>) -> Unit) {
            updateAction = update
        }

        fun doInBg(background: (Array<out Params>) -> Result) {
            backgroundAction = background
        }

        fun onPostExecute(action: (Result) -> Unit) {
            postExcuteAction = action
        }

        fun publishProgress(vararg values: Progress) {
            this@AsyncTaskKTX.publishProgress(*values)
        }

        fun getAsyncTask() = this@AsyncTaskKTX
    }
}
