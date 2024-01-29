package com.example.fakeacquirer

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fakeacquirer.ui.theme.FakeAcquirerTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


class FakeAcquirerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FakeAcquirerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MakeSuccess()
                }
            }
        }
    }
}

@Composable
fun MakeSuccess() {
    val context: Context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var textButtonPayment by remember { mutableStateOf("Pagamento com sucesso") }
        var textButtonFailed by remember { mutableStateOf("Pagamento não autorizado") }
        Column {
            Button(
                modifier = Modifier.padding(8.dp),
                onClick = {
                    textButtonPayment = "Realizando pagamento..."

                    MainScope().launch {
                        FakeAcquirerSdk(context).makeTransactionSuccess(object: FakeAcquirerCallback{
                            override fun transactionWithSuccess(fakeAcquirerResponse: FakeAcquirerResponse) {
                                Log.d("FakeAcquirerLOG", fakeAcquirerResponse.toString())
                                textButtonPayment = "Pagamento realizado com sucesso!"
                            }

                            override fun transactionFailed(fakeAcquirerResponse: FakeAcquirerResponse) {
                                Log.d("FakeAcquirerLOG", fakeAcquirerResponse.toString())
                                textButtonPayment = "Desculpe ocorreu um erro!"
                            }
                        })
                    }
                }
            ) {
                Text(text = textButtonPayment)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                textButtonPayment = "Realizando pagamento..."
                MainScope().launch {
                    FakeAcquirerSdk(context).makeTransactionFailed(object: FakeAcquirerCallback{
                        override fun transactionWithSuccess(fakeAcquirerResponse: FakeAcquirerResponse) {
                            Log.d("FakeAcquirerLOG", fakeAcquirerResponse.toString())
                            textButtonFailed = "Pagamento realizado com sucesso!"
                        }

                        override fun transactionFailed(fakeAcquirerResponse: FakeAcquirerResponse) {
                            Log.d("FakeAcquirerLOG", fakeAcquirerResponse.toString())
                            textButtonFailed = "Desculpe ocorreu um erro!"
                        }
                    })
                }
            },
            modifier = Modifier.padding(8.dp),
            colors =  ButtonDefaults.buttonColors(
                containerColor = Color.LightGray
            ),
        ) {
            Text(textButtonFailed, textAlign = TextAlign.Center)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                throw Exception("Exception Lançanda com sucesso!")
            },
            modifier = Modifier.padding(8.dp),
            colors =  ButtonDefaults.buttonColors(
                containerColor = Color.Red
            ),
        ) {
            Text("Lançar Exception", textAlign = TextAlign.Center)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                throw Throwable("Throwable Lançanda com sucesso!")
            },
            modifier = Modifier.padding(8.dp),
            colors =  ButtonDefaults.buttonColors(
                containerColor = Color.Red
            ),
        ) {
            Text("Lançar Throwable", textAlign = TextAlign.Center)
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun MakeSuccessContentPreview() {
    FakeAcquirerTheme {
        MakeSuccess()
    }
}