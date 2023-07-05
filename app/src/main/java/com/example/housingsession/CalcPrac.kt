package com.example.housingsession

import androidx.compose.foundation.layout.Row
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.housingsession.ui.theme.HousingSessionTheme

class CalcPrac : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HousingSessionTheme {
                // A surface container using the 'background' color from the theme
               // @OptIn(ExperimentalMaterial3Api::class)
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Greeting2("Android")
                    Calculator(applicationContext)
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Calculator(applicationContext: Context){
    var a = remember {
        mutableStateOf("")
    }
    var b = remember {
        mutableStateOf("")
    }
    var result = remember{
        mutableStateOf("")
    }
    var bool = remember{
        mutableStateOf(false)
    }
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Yellow)
            .paint(painterResource(id = R.drawable.bg), contentScale = ContentScale.FillBounds), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        TextField(
            value = a.value,
            onValueChange = {
                    newText -> a.value = newText
            },
            label = { Text(text = "Number 1", fontSize = 32.sp) },
            placeholder = { Text(text = "Type your number") }
        )
        TextField(
            value = b.value,
            onValueChange = {
                    newText -> b.value = newText
            },
            label = { Text(text = "Number 2", fontSize = 32.sp) },
            placeholder = { Text(text = "Type your number") }
        )
        Row() {
            Button(onClick = { /*TODO*/
                result.value = (a.value.toInt()+b.value.toInt()).toString()
                bool.value = true

                //Toast.makeText(applicationContext,"added", Toast.LENGTH_SHORT).show()
            }) {
                Text(text = "ADD")
            }
            Button(onClick = { /*TODO*/
                result.value = (a.value.toInt()-b.value.toInt()).toString()
                bool.value = true
            }) {
                Text(text = "SUB")
            }
            Button(onClick = { /*TODO*/
                result.value = (a.value.toInt()*b.value.toInt()).toString()
                bool.value = true
            }) {
                Text(text = "MUL")
            }
            Button(onClick = { /*TODO*/
                result.value = (a.value.toDouble()/b.value.toDouble()).toString()
                bool.value = true
            }) {
                Text(text = "DIV")
            }
            Button(onClick = { /*TODO*/
                result.value = (a.value.toDouble()/b.value.toDouble()).toString()
                bool.value = true
            }) {
                Text(text = "Do nothing")
            }
        }

        if(bool.value){
            Row() {
                Text(text = "${result.value}", fontSize = 32.sp)
            }
        }


    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    HousingSessionTheme {
        Greeting2("Android")
    }
}