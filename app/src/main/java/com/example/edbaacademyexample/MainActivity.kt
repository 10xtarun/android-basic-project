package com.example.edbaacademyexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.edbaacademyexample.ui.theme.EdbaAcademyExampleTheme

class MainActivity : ComponentActivity() {
    private lateinit var myName: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EdbaAcademyExampleTheme {
                var name = remember {
                    mutableStateOf("")
                }
                var names = remember {
                    mutableStateOf(emptyList<String>())
                }
               Column(
                   modifier = Modifier
                       .fillMaxSize()
                       .padding(24.dp)
                       .padding(top = 10.dp)
               ){
                   Row (
                       modifier = Modifier.fillMaxWidth()
                   ){
                       OutlinedTextField(
                           value = name.value,
                           onValueChange = { text ->
                               name.value = text
                           },
                           modifier = Modifier.weight(1f)
                       )
                       Spacer(modifier = Modifier.width(16.dp))
                       Button(
                           onClick = {
                               if(name.value.isNotBlank()) {
                                   names.value = names.value.plus(name.value)
                               }
                               name.value = ""
                           }
                       ) {
                           Text(text = "Add")
                       }
                   }

                   LazyColumn {
                       items(names.value) { currentValue ->
                           Text(
                               text = currentValue,
                               modifier = Modifier
                                   .fillMaxWidth()
                                   .padding(16.dp)
                           )
                           HorizontalDivider()
                       }
                   }
               }
            }
        }
    }
}

/**
 *  var count = remember {  mutableStateOf(0) }
 *                 Column(
 *                     modifier = Modifier.fillMaxSize(),
 *                     verticalArrangement = Arrangement.Center,
 *                     horizontalAlignment = Alignment.CenterHorizontally
 *                 ) {
 *                     Text(
 *                         text = count.value.toString(),
 *                         fontSize = 30.sp
 *                     )
 *                     Button(onClick = {
 *                         count.value += 1
 *                     }) {
 *                         Text(text = "Click Me!")
 *                     }
 *                 }
 */