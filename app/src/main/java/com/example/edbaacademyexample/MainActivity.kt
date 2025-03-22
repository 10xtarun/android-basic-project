package com.example.edbaacademyexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.edbaacademyexample.ui.theme.EdbaAcademyExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            EdbaAcademyExampleTheme {

                var todoItem = remember {
                    mutableStateOf("")
                }
                var todos = remember {
                    mutableStateOf(emptyList<String>())
                }
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   Column(
                       modifier = Modifier
                           .fillMaxSize()
                           .padding(top = 20.dp, bottom = 10.dp)
                           .padding(all = 5.dp)
                       ,
                       horizontalAlignment = Alignment.CenterHorizontally,
                       verticalArrangement = Arrangement.Top,
                   ) {

                       Row {
                           Text(
                               text = "Your Todo List!",
                               fontSize = 36.sp,
                               fontWeight = FontWeight.Bold,
                               modifier = Modifier
                                   .padding(5.dp)
                           )
                       }

                       Row (
                           horizontalArrangement = Arrangement.Center,
                           verticalAlignment = Alignment.CenterVertically
                       ) {
                           OutlinedTextField(
                               value = todoItem.value,
                               onValueChange = { text ->
                                   todoItem.value = text
                                   println("=== ${todoItem.value}")
                               },
                               modifier = Modifier
                                   .padding(12.dp)
                           )

                           Button(
                               onClick = {
                                   if(todoItem.value.isNotBlank()){
                                       todos.value += todoItem.value
                                   }
                                   todoItem.value = ""

                                   println("=== ${todos.value}")
                               },
                               shape = RoundedCornerShape(5f),
                               modifier = Modifier
                                   .height(intrinsicSize = IntrinsicSize.Max)
                           ) {
                               Text(
                                   text = "Add",
                                   fontSize = 16.sp,
                                   modifier = Modifier
                               )
                           }
                       }

                       Row  (
                           verticalAlignment = Alignment.CenterVertically
                       )  {
                           LazyColumn  {
                               items(todos.value){ item ->
                                   Text(
                                       text = item,
                                       fontSize = 24.sp,
                                       fontWeight = FontWeight.Medium,
                                       modifier = Modifier
                                           .padding(12.dp)
                                   )
                                   HorizontalDivider()
                               }
                           }
                       }
                   }
                }
            }
        }
    }
}
