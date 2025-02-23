package com.example.edbaacademyexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.edbaacademyexample.ui.theme.EdbaAcademyExampleTheme

class MainActivity : ComponentActivity() {
    private lateinit var myName: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        myName = "Bruce Wayne"
        val message = if(::myName.isInitialized) {
            myName
        } else {
            "Default Text"
        }

        val currentNumber = 2

        var switchedVar = when(currentNumber) {
            1 -> {
                "This is one."
            }
            2 -> {
                "This is two."
            }
            3 -> {
                "This is three."
            }
            else -> {
                "Default Text"
            }
        }

        setContent {
            EdbaAcademyExampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = message,
                        modifier = Modifier
                            .padding(innerPadding),
                        message = switchedVar
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier,
    message: String? = null,
) {

    println("===is ${message is String}")
    Column {
        Text(
            text = "Greetings $name!",
            modifier = modifier
        )

        val finalMessage = message ?: "Default Message"
        Text(
            text = "New Text: $finalMessage",
            modifier = modifier
        )

        val a = A()
        a.message(value = finalMessage)

        val b = B()
        b.message(finalMessage, "Bruce Wayne")

//        fun intoX(value: Int, x: Int) = value * x
//        println("Multiply, intoX: ${intoX(2, 5)}")

        val op = toMyList(value = finalMessage)
        println("===op $op")

        val finalNumber = 123456
        val op1 = toMyList(value = finalNumber)
        println("===op1 $op1")

        val xyz = "String"
        xyz.toAnyList(xyz)

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EdbaAcademyExampleTheme {
        Greeting("Android")
    }
}

open class A() {
    fun message(value: String) {
        println("Hello: $value ")
    }
}

class B: A() {
    fun message(value: String, name: String) {
        println("Greetings: $value , $name")
    }
}

fun <T> toMyList(value: T): List<T> {
    val myList: MutableList<T> = mutableListOf<T>()
    myList.add(value)
    return myList.toList()
}

fun <T> Any.toAnyList(value: T): List<T> = toMyList(value)