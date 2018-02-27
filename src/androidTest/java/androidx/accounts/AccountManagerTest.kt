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
import android.annotation.SuppressLint
import android.os.Bundle
import android.support.test.InstrumentationRegistry
import androidx.kotlin.test.R
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

@SuppressLint("MissingPermission")
class AccountManagerTest {

    private val context = InstrumentationRegistry.getContext()

    private val accountManager = AccountManager.get(context)

    private val accountType = context.getString(R.string.account_type)

    private val account1 = Account("Account 1", accountType)
    private val account2 = Account("Account 2", accountType)

    @After fun after() {
        accountManager.getAccountsByType(accountType).forEach {
            accountManager.removeAccountExplicitly(it)
        }
    }

    @Test fun testRemoveAccount() {
        accountManager.addAccountExplicitly(account1, "password", Bundle.EMPTY)
        accountManager.addAccountExplicitly(account2, "password", Bundle.EMPTY)

        assertEquals(
            "2 Accounts have been added",
            2,
            accountManager.getAccountsByType(accountType).size
        )

        accountManager.removeAccount(account1) { accountManagerFuture ->
            assertTrue(
                "Account removal successful",
                accountManagerFuture.result.getBoolean(AccountManager.KEY_BOOLEAN_RESULT)
            )

            assertEquals(
                "1 Account has been removed",
                1,
                accountManager.getAccountsByType(accountType).size
            )
        }
    }
}
