package com.example.fakeacquirer

import android.os.Bundle
import androidx.activity.ComponentActivity


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FakeAcquirerSdk(context = baseContext).start()
        finish()
    }
}