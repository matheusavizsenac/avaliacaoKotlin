package com.example.avaliacaomobilegorjeta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.avaliacaomobilegorjeta.ui.theme.AvaliacaoMobileGorjetaTheme
import kotlin.math.roundToInt


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AvaliacaoMobileGorjetaTheme() {
                Surface(modifier = Modifier.fillMaxSize()) {
                    telaprincipal()
                }
            }
        }
    }
}


@Composable
fun telaprincipal(){
    var calc = Calculos()
    var full15 = remember {
        mutableStateOf("")
    }
    var valor = remember {
        mutableStateOf("")
    }
    var total = remember {
        mutableStateOf("")
    }
    var gorj15 = remember {
        mutableStateOf("")
    }
    var custGorj = remember {
        mutableStateOf("")
    }
    var sliderPosition by remember { mutableStateOf(10.toFloat()) }

    Column(
        modifier = Modifier.padding(20.dp)
    ) {
        Text(text = "Calculadora")
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Valor") },
            value = valor.value,
            onValueChange = {
                valor.value = it
                full15.value = (it.toFloat() * 0.15 + it.toFloat()).toString()
                gorj15.value = (it.toFloat() * 0.15).toString()
                custGorj.value = calc.calcularGorjeta(valor.value.toFloat(), sliderPosition).toString()
                total.value = calc.calcularTotalGorjeta(valor.value.toFloat(), sliderPosition).toString()
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
        )
        Text(
            text = "Porcentagem: " + sliderPosition.toString()
        )
        Slider(
            modifier = Modifier.semantics { contentDescription = "Localized Description" },
            value = sliderPosition,
            onValueChange = {
                sliderPosition = it.roundToInt().toFloat()
                custGorj.value =
                    calc.calcularGorjeta(valor.value.toFloat(), sliderPosition).toString()
                total.value =
                    calc.calcularTotalGorjeta(valor.value.toFloat(), sliderPosition).toString()
            },
            valueRange = 0f..30f,
            steps = 30,
        )

        Row() {
            Column(modifier = Modifier
                .width(155.dp)
                .padding(10.dp)) {
                OutlinedTextField(

                    value = gorj15.value,
                    onValueChange = {
                        gorj15.value = it
                    },
                    readOnly = true,
                    label = { Text(text = "15% Gorjeta") }
                )
                OutlinedTextField(
                    value = full15.value,
                    onValueChange = {
                        full15.value = it
                    },
                    readOnly = true,
                    label = { Text(text = "Total 15% de gorjeta") }
                )
            }
            Column(modifier = Modifier
                .width(155.dp)
                .padding(10.dp)) {
                OutlinedTextField(
                    label = { Text(text = sliderPosition.roundToInt().toString() + "% Gorjeta") },
                    value = custGorj.value,
                    onValueChange = {
                        custGorj.value = it
                    },
                    readOnly = true,
                )
                OutlinedTextField(
                    label = { Text(text = "Total " + sliderPosition.roundToInt().toString() + "% Gorjeta") },
                    value = total.value,
                    onValueChange = {
                        total.value = it
                    },
                    readOnly = true,
                )
            }

        }

    }

}

