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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.edbaacademyexample.models.Posts
import com.example.edbaacademyexample.ui.theme.EdbaAcademyExampleTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val networkManager = NetworkManager()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            EdbaAcademyExampleTheme {
                var posts : MutableState<List<Posts>> = remember {
                    mutableStateOf(emptyList())
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
                               text = "Your Posts List",
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
                           Button(
                               onClick = {
                                   CoroutineScope(Dispatchers.IO).launch {
                                       val response = networkManager.fetchPosts()
                                       posts.value = response
                                   }
                                   println("=== clicked!")
                               },
                               shape = RoundedCornerShape(5f),
                               modifier = Modifier
                                   .height(intrinsicSize = IntrinsicSize.Max)
                                   .fillMaxWidth()
                           ) {
                               Text(
                                   text = "Fetch Posts",
                                   fontSize = 20.sp,
                                   modifier = Modifier
                               )
                           }
                       }

                       Row  (
                           verticalAlignment = Alignment.CenterVertically
                       )  {
                           LazyColumn {

                               items(posts.value){ postItem ->

                                       Card(
                                           colors = CardDefaults.cardColors(
                                               containerColor = MaterialTheme.colorScheme.surfaceVariant,
                                           ),
                                           modifier = Modifier
                                               .fillMaxWidth()
                                               .height(120.dp)
                                               .padding(10.dp)
                                       ) {
                                           Column(
                                               modifier = Modifier.fillMaxWidth()
                                           ) {
                                               Row (
                                                   modifier = Modifier.fillMaxWidth()
                                               ) {
                                                   Text(
                                                       text = "By User: ${postItem.userId}",
                                                       modifier = Modifier
                                                           .width(intrinsicSize = IntrinsicSize.Max)
                                                           .padding(8.dp)
                                                       ,
                                                       textAlign = TextAlign.Left,

                                                   )

                                                   Text(
                                                       text = "Post Id: ${postItem.id}",
                                                       modifier = Modifier
                                                           .width(intrinsicSize = IntrinsicSize.Max)
                                                           .padding(8.dp)
                                                       ,
                                                       textAlign = TextAlign.Right,
                                                   )
                                               }

                                               Row {
                                                   Text(
                                                       text = postItem.title.toString(),
                                                       modifier = Modifier
                                                           .padding(8.dp)
                                                       ,
                                                       fontWeight = FontWeight.Bold,
                                                       textAlign = TextAlign.Center,

                                                   )
                                               }

                                           }
                                       }
                               }
                           }
                       }
                   }
                }
            }
        }
    }
}
