package com.example.housingsession

import android.Manifest
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.housingsession.ui.theme.HousingSessionTheme
import kotlin.math.atanh

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


                    ExampleScreen()
                    Greeting3(applicationContext)
                    
                    //artt(applicationContext)

                }
            }
        }
    }
}
//notificationmanager <-n

// designing -> title, messg, acion, icon -> n

@Composable
fun Greeting3(context: Context) {
    Column() {
        var b by remember {
            mutableStateOf(false)
        }
        var notf by remember {
            mutableStateOf(false)
        }

        var mp = MediaPlayer.create(context, R.raw.kgf)

        val lis = OnDateSetListener { view, year, month, dayOfMonth ->
            Toast.makeText(context, "$dayOfMonth / ${month + 1} / $year", Toast.LENGTH_LONG).show()

        }
        if (notf){
            artt(applicationContext = LocalContext.current)
        }

        if (b) {
         val at =  AlertDialog(
                onDismissRequest = {



                },

                title = { Text(text = "Save Success!", color = Color.Black) },
                text = { Text("Hello! This is our Alert Dialog..", color = Color.Black) },
                confirmButton = {
                    TextButton(
                        onClick = {
                            Toast.makeText(context, "Confirm Button Click", Toast.LENGTH_LONG)
                                .show()
                        }
                    ) {
                        Text("Confirm", color = Color.Blue)
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            b=false

                            Toast.makeText(context, "Dismiss Button Click", Toast.LENGTH_LONG)
                                .show()
                        }
                    ) {
                        Text("Dismiss", color = Color.Blue)
                    }
                },
             containerColor = Color.Green
            )
        }
        val dp = DatePickerDialog(context, lis, 2023, 0, 1)

        Button(onClick = { dp.show() }) {
            Text(text = "Date picker")
        }
        Button(onClick = {
            mp.start()
        }) {
            Text(text = "Play")
        }
        Button(onClick = {
            mp.pause()
        }) {
            Text(text = "Pause")
        }
        Button(onClick = {
            mp.stop()
            mp = MediaPlayer.create(context, R.raw.kgf)

        }) {
            Text(text = "Stop")
        }
        Button(onClick = {
            b = true
        }) {
            Text(text = "alert")
        }
        Button(onClick = {
            notf = true
        }) {
            Text(text = "show notification")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    HousingSessionTheme {
//        Greeting3("")
    }
}


@Composable
fun artt(applicationContext:Context) {

    var notificationManager =
        applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val notificationChannel =
            NotificationChannel("101", "channel",
                NotificationManager.IMPORTANCE_DEFAULT)
        notificationManager.createNotificationChannel(notificationChannel)
    }

    var inte = Intent(applicationContext, LazyComp::class.java)
    var pendin = PendingIntent.getActivity(
        applicationContext,
        0,
        inte,
        PendingIntent.FLAG_IMMUTABLE
    )
    val notificationBuilder = NotificationCompat.Builder(applicationContext, "101")
        .setContentTitle("All is Well")
        .setContentText("Everything is happening perfectly!")
        .setContentIntent(pendin)
        .setSmallIcon(R.drawable.ic_launcher_background)

    notificationManager.notify(1, notificationBuilder.build())

}

@Composable
fun ExampleScreen() {
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // Permission Accepted: Do something
            Log.d("ExampleScreen","PERMISSION GRANTED")

        } else {
            // Permission Denied: Do something
            Log.d("ExampleScreen","PERMISSION DENIED")
        }
    }
    val context = LocalContext.current


Column() {

    Button(
        onClick = {
            // Check permission
            when (PackageManager.PERMISSION_GRANTED) {
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) -> {
                    // Some works that require permission
                    Log.d("ExampleScreen", "Code requires permission")
                }

                else -> {
                    // Asking for permission
                    launcher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                }
            }
        }
    ) {
        Text(text = "Check and Request Permission")
    }
}
}
