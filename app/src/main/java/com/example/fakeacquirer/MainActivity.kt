package com.example.fakeacquirer

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.fakeacquirer.FakeAcquirerSdk.makeTransactionFailed
import com.example.fakeacquirer.FakeAcquirerSdk.makeTransactionWith
import com.example.fakeacquirer.ui.theme.FakeAcquirerTheme
import kotlinx.coroutines.runBlocking


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FakeAcquirerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CenteredContent()
                }
            }
        }
    }
}

@Composable
fun CenteredContent() {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                runBlocking {
                    FakeAcquirerSdk.makeTransactionWith(object: FakeAcquirerCallback{
                        override fun transactionWithSuccess(fakeAcquirerResponse: FakeAcquirerResponse) {
                            Log.d("FakeAcquirerLOG", fakeAcquirerResponse.toString())
                        }

                        override fun transactionFailed(fakeAcquirerResponse: FakeAcquirerResponse) {
                            Log.d("FakeAcquirerLOG", fakeAcquirerResponse.toString())
                        }
                    })
                }
            },
            modifier = Modifier.padding(8.dp)
        ) {
            Text("Pagamento realizado com Sucesso!")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                runBlocking {
                    FakeAcquirerSdk.makeTransactionFailed(object: FakeAcquirerCallback{
                        override fun transactionWithSuccess(fakeAcquirerResponse: FakeAcquirerResponse) {
                            Log.d("FakeAcquirerLOG", fakeAcquirerResponse.toString())
                        }

                        override fun transactionFailed(fakeAcquirerResponse: FakeAcquirerResponse) {
                            Log.d("FakeAcquirerLOG", fakeAcquirerResponse.toString())
                        }
                    })
                }
            },
            modifier = Modifier.padding(8.dp),
            colors =  ButtonDefaults.buttonColors(
                containerColor = Color.LightGray
            ),
        ) {
            Text("Pagamento não realizado com Sucesso!", textAlign = TextAlign.Center)
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
            Text("Lançar Exception!", textAlign = TextAlign.Center)
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
            Text("Lançar Throwable!", textAlign = TextAlign.Center)
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun CenteredContentPreview() {
    FakeAcquirerTheme {
        CenteredContent()
    }
}