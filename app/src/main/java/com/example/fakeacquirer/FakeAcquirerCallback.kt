package com.example.fakeacquirer

interface FakeAcquirerCallback {
    fun transactionWithSuccess(fakeAcquirerResponse: FakeAcquirerResponse)
    fun transactionFailed(fakeAcquirerResponse: FakeAcquirerResponse)
}