package com.example.searchbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.searchbar.ui.theme.SearchBarTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        val names = listOf(
            "Hany", "Ziad", "Hassan", "Khaled", "Raed", "Ewida", "Omar", "Moaz",
        )
        setContent {
            SearchBarTheme {
                SearchScreen(names)

            }
        }
    }
}

@Composable
fun SearchScreen(names: List<String>) {
    var searchQuery by remember { mutableStateOf("") }
    val newNamesList = if (searchQuery.isBlank()) {
        names
    } else {
        names.filter { name ->
            name.startsWith(searchQuery, ignoreCase = true)
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = searchQuery, onValueChange = { query ->
                searchQuery = query
            }, label = { Text("name") }, modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(newNamesList.size) { index ->
                NameRow(newNamesList[index])
            }
        }
    }
}

@Composable
private fun NameRow(name: String) {
    Row(
        Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Text(name, fontSize = 25.sp)
        Spacer(modifier = Modifier.height(16.dp))
    }
}

