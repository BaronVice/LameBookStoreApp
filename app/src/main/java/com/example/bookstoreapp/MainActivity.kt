package com.example.bookstoreapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bookstoreapp.data.Book
import com.example.bookstoreapp.ui.theme.BookStoreAppTheme
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    val fs = Firebase.firestore
    val list = remember {
        mutableStateOf(emptyList<Book>())
    }

    // TODO: "books" to constants
    val listener = fs.collection("books").addSnapshotListener {
        snapshot, exception -> list.value = snapshot?.toObjects(Book::class.java) ?: emptyList()
    }
    // listener.remove if snapShotListener is not needed


    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        LazyColumn (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
        ) {
            items(list.value){
                book -> Card(
                    modifier = Modifier.fillMaxWidth().padding(10.dp)
                ){
                    Text(
                        text = book.name,
                        modifier = Modifier.fillMaxWidth().wrapContentWidth().padding(15.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
        Button(
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            onClick = {
            fs.collection("books")
                .document()
                .set(
                    Book(
                        "Clowns and balloons",
                        "Real tricks, no gimmicks",
                        "13.37",
                        "Comedy",
                        "url :|"
                    )
                )
        }) {
            Text(
                text = "Add Book",
            )
        }
    }
}