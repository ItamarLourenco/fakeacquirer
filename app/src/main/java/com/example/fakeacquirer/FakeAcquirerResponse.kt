package com.example.fakeacquirer

import java.util.UUID

enum class StatusTransaction {
    SUCCESS,
    FAILED,
}


class FakeAcquirerResponse(val price: Float?,
                           val status: StatusTransaction?) {
    val id: String = UUID.randomUUID().toString()

    override fun toString(): String {
        return "FakeAcquirerResponse(" +
                "id=$id, " +
                "price=$price, " +
                "status=$status" + ")"
    }
}