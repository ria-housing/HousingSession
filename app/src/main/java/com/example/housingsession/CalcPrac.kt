package com.example.housingsession

import androidx.compose.foundation.layout.Row
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                    Greeting2()
                   // tes()
                   // Calculator(applicationContext)
                }
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Calculator() {
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
            //hi
        }

        if(bool.value){

            Row() {
                Text(text = "${result?.value}", fontSize = 32.sp)
            }
        }


    }
}

@Composable
fun Greeting2() {

    Column() {
        val uriHandler = LocalUriHandler.current


        val annotatedString = buildAnnotatedString {
            append("By joining, you agree to the ")

            pushStringAnnotation(tag = "policy", annotation = "https://google.com/policy")
            withStyle(style = SpanStyle(color = Color.Blue)) {
                append("privacy policy")
            }
            pop()

            append(" and ")

            pushStringAnnotation(tag = "terms", annotation = "https://google.com/terms")

            withStyle(style = SpanStyle(color = Color.Blue)) {
                append("terms of use")
            }

            pop()
        }

        ClickableText(
            text = annotatedString,
            style = MaterialTheme.typography.bodyLarge,
            onClick = { offset ->
                annotatedString.getStringAnnotations(tag = "policy", start = offset, end = offset)
                    .firstOrNull()?.let {
                    Log.d("policy URL", it.item)
uriHandler.openUri(uri = it.item)
                }

                annotatedString.getStringAnnotations(tag = "terms", start = offset, end = offset)
                    .firstOrNull()?.let {
                    Log.d("terms URL", it.item)
                        uriHandler.openUri(uri = it.item)

                }
            })
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    HousingSessionTheme {
        Greeting2()
    }
}

@Composable
fun tes() {
    val annotatedLinkString: AnnotatedString = buildAnnotatedString {
        val uriHandler = LocalUriHandler.current

        val str = "Click this link to go to web site"
        val startIndex = str.indexOf("link")
        val endIndex = startIndex + 4
        append(str)
        addStyle(
            style = SpanStyle(
                color = Color(0xff64B5F6),
                fontSize = 18.sp,
                textDecoration = TextDecoration.Underline
            ), start = startIndex, end = endIndex
        )

        // attach a string annotation that stores a URL to the text "link"
        addStringAnnotation(
            tag = "URL",
            annotation = "https://github.com",
            start = startIndex,
            end = endIndex
        )

    }

// UriHandler parse and opens URI inside AnnotatedString Item in Browse
    val uriHandler = LocalUriHandler.current

// 🔥 Clickable text returns position of text that is clicked in onClick callback
    ClickableText(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        text = annotatedLinkString,
        onClick = {
            annotatedLinkString
                .getStringAnnotations("URL", it, it)
                .firstOrNull()?.let { stringAnnotation ->
                    uriHandler.openUri(stringAnnotation.item)
                }
        }
    )
}