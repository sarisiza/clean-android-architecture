package com.upakon.usingkotlincoroutines

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.upakon.usingkotlincoroutines.ui.theme.UsingKotlinCoroutinesTheme
import com.upakon.usingkotlincoroutines.viewModel.SumViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UsingKotlinCoroutinesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel : SumViewModel = viewModel()
                    SumNumbersScreen(viewModel = viewModel)
                }
            }
        }
    }
}

@Composable
fun SumNumbersScreen(
    viewModel: SumViewModel
) {
    var firstNumber by remember {
        mutableStateOf("0")
    }
    var secondNumber by remember {
        mutableStateOf("0")
    }
    val timer = viewModel.timerState.collectAsState()
    val sum = viewModel.sum.collectAsState()
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = firstNumber,
            onValueChange = {firstNumber = it},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )
        OutlinedTextField(
            value = secondNumber,
            onValueChange = {secondNumber = it},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            )
        )
        Button(
            onClick = {
                viewModel.sumNumbers(firstNumber.toInt(),secondNumber.toInt(),5)
            }
        ) {
            Text(text = "Calculate!")
        }
        Text(text = timer.value)
        Text(text = sum.value.toString())
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UsingKotlinCoroutinesTheme {
    }
}