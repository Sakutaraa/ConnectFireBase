package com.example.newcanaryproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newcanaryproject.ui.theme.NewcanaryprojectTheme
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewcanaryprojectTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen()
                }
            }
        }
    }

    @Composable
    fun HomeScreen() {
        val database = Firebase.database
        val myRef = database.getReference("message")

        var text by remember { mutableStateOf("") }
        Column(modifier = Modifier.padding(16.dp)) {
            TextField(value = text, onValueChange = { newtext ->
                text =
                    newtext
            },
                label = { Text(text = "enter your data") }
            )
            Button(
                onClick = { myRef.setValue(text) },
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = "Submit")
            }
        }
    }
}