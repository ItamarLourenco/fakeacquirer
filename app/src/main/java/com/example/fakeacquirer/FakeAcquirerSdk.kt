package com.example.fakeacquirer

import android.content.Context
import android.content.Intent
import android.util.Log
import kotlinx.coroutines.delay

class FakeAcquirerSdk(val context: Context) {
    fun start() {
        context.startActivity(
            Intent(context, FakeAcquirerActivity::class.java)
        )
    }

    suspend fun makeTransactionSuccess(fakeAcquirerCallback: FakeAcquirerCallback) {
        Log.i("FakeAcquirerLOG", "Iniciando transação com sucesso")

        delay(2000)

        fakeAcquirerCallback.transactionWithSuccess(
            FakeAcquirerResponse(
                price = 1.0f,
                status = StatusTransaction.SUCCESS
            )
        )
        Log.i("FakeAcquirerLOG", "Finalizando transação com sucesso")
    }

    suspend fun makeTransactionFailed(fakeAcquirerCallback: FakeAcquirerCallback) {
        Log.i("FakeAcquirerLOG", "Iniciando transação com Falha")

        delay(2000)
        fakeAcquirerCallback.transactionFailed(
            FakeAcquirerResponse(
                price = 1.0f,
                status = StatusTransaction.SUCCESS
            )
        )
        Log.i("FakeAcquirerLOG", "Finalizando transação com falha")
    }
}