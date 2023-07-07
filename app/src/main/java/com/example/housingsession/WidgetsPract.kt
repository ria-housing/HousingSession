package com.example.housingsession

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.housingsession.ui.theme.HousingSessionTheme

class WidgetsPract : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HousingSessionTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val ddd = intent.getStringExtra("efssf")
                    val sss = intent.getIntExtra("efssf",6)
                    println(ddd)
                  //  Greeting3("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting3(name: String, modifier: Modifier = Modifier) {
   Column() {
       val ctx= LocalContext.current
       var mp = MediaPlayer.create(ctx,R.raw.kgf)

       val lis = OnDateSetListener {
               view, year, month, dayOfMonth ->
           Toast.makeText(ctx,"$dayOfMonth / ${month+1} / $year",Toast.LENGTH_LONG).show()

       }

       val dp = DatePickerDialog(ctx,lis,2023,0,1)

       Button(onClick = { dp.show() }) {
           Text(text = "Date picker")
       }
       Button(onClick = {
           mp.start()
       }) {
           Text(text = "Play")
       }
       Button(onClick = {            mp.pause()
       }) {
           Text(text = "Pause")
       }
       Button(onClick = {            mp.stop()
             mp = MediaPlayer.create(ctx,R.raw.kgf)

       }) {
           Text(text = "Stop")
       }
   }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    HousingSessionTheme {
        Greeting3("Android")
    }
}