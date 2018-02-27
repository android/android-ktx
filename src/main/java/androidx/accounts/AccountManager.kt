@file:JvmName("AccountManagerExt")

/*
 * Copyright (C) 2017 The Android Open Source Project
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

package androidx.accounts

import android.accounts.Account
import android.accounts.AccountManager
import android.accounts.AccountManagerFuture
import android.accounts.OperationCanceledException
import android.accounts.AuthenticatorException
import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.support.annotation.RequiresPermission
import androidx.os.bundleOf
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * Removes an account from the AccountManager. Does nothing if the account
 * does not exist.  Does not delete the account from the server.
 * The authenticator may have its own policies preventing account
 * deletion, in which case the account will not be deleted.
 *
 * <p>This method may be called from any thread, but the returned
 * {@link AccountManagerFuture} must not be used on the main thread.
 *
 * <p>This method requires the caller to have a signature match with the
 * authenticator that manages the specified account.
 *
 * <p><b>NOTE:</b> If targeting your app to work on API level 22 and before,
 * MANAGE_ACCOUNTS permission is needed for those platforms. See docs for
 * this function in API level 22.
 *
 *
 * In API 22 (LOLLIPOP_MR1),
 * AccountManager.removeAccount(Account, Activity, AccountManagerCallback<Bundle>, Handler) was added, and
 * AccountManager.removeAccount(Account, AccountManagerCallback<Boolean>, Handler) was deprecated.
 *
 * To resolve this when running on a pre-22 device, call the old removeAccount(), get the Boolean result,
 * put it in a Bundle using key AccountManager.KEY_BOOLEAN_RESULT,
 * then call AccountManagerCallback<Bundle> callback.run() with a new AccountManagerFuture<Bundle>
 * that returns this Bundle.
 *
 *
 * @param account The {@link Account} to remove
 * @param activity The {@link Activity} context to use for launching a new
 *     authenticator-defined sub-Activity to prompt the user to delete an
 *     account; used only to call startActivity(); if null, the prompt
 *     will not be launched directly, but the {@link Intent} may be
 *     returned to the caller instead
 * @param handler {@link Handler} identifying the callback thread,
 *     null for the main thread
 * @param callback Callback to invoke when the request completes,
 *     null for no callback
 */
@RequiresPermission("android.permission.MANAGE_ACCOUNTS")
fun AccountManager.removeAccount(
    account: Account,
    activity: Activity? = null,
    handler: Handler? = null,
    callback: ((AccountManagerFuture<Bundle>) -> Unit)? = null
) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
        removeAccount(account, activity, callback, handler)
    } else {
        @Suppress("DEPRECATION")
        removeAccount(
            account,
            { future ->
                callback?.invoke(object : AccountManagerFuture<Bundle> {
                    override fun cancel(mayInterruptIfRunning: Boolean) =
                        future.cancel(mayInterruptIfRunning)

                    override fun isCancelled() = future.isCancelled

                    override fun isDone() = future.isDone

                    @Throws(
                        OperationCanceledException::class,
                        IOException::class,
                        AuthenticatorException::class
                    )
                    override fun getResult() = bundleOf(
                        AccountManager.KEY_BOOLEAN_RESULT to future.result
                    )

                    @Throws(
                        OperationCanceledException::class,
                        IOException::class,
                        AuthenticatorException::class
                    )
                    override fun getResult(timeout: Long, unit: TimeUnit) = bundleOf(
                        AccountManager.KEY_BOOLEAN_RESULT to future.getResult(timeout, unit)
                    )
                })
            },
            handler
        )
    }
}
