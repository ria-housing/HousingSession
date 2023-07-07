@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.housingsession

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.housingsession.ui.theme.HousingSessionTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HousingSessionTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   // startActivity(Intent(applicationContext,WidgetsPract::class.java))

                    //intent(this,WidgetsPract::class.java)
                 val k =   Intent(this,WidgetsPract::class.java)
                     .putExtra("age",27)
                     .putExtra("efssf","segfes")
                     .putExtra("fgsrgrdrh","sgsrsgdg")
                    //.putExtra("hi",)
                    startActivity(k)

                    MainScreen(applicationContext)

//                    Greeting("Android")


                }
            }
        }
    }
}

@Composable
fun MainScreen(applicationContext: Context){
    var email = remember {
        mutableStateOf("")
    }
    var password = remember {
        mutableStateOf("")
    }
    var bool = remember{
        mutableStateOf(false)
    }
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Yellow), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        TextField(
            value = email.value,
            onValueChange = {
                    newText -> email.value = newText
            },
            label = { Text(text = "Email", fontSize = 32.sp) },
            placeholder = { Text(text = "Type your email") }
        )
        TextField(value = password.value, onValueChange = {
                newText -> password.value = newText
        },
            label = { Text(text = "Password",fontSize = 32.sp) },
            placeholder = { Text(text = "Type your password") })
        Row() {
            Button(onClick = { /*TODO*/
                bool.value = true
                Toast.makeText(applicationContext,"added",Toast.LENGTH_SHORT).show()
            }) {
                Text(text = "ADD")
            }
            Button(onClick = { /*TODO*/
                bool.value = true}) {
                Text(text = "SUBMIT")
            }
        }
        if(bool.value){
            Text(text = "email: ${email.value} , password: ${password.value}")
        }
    }



}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HousingSessionTheme {
        Greeting("Android")
    }
}