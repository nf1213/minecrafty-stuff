package com.example.minecraftystuff

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.minecraftystuff.ui.theme.MinecraftyStuffTheme
import com.example.minecraftystuff.ui.theme.Purple200
import com.example.minecraftystuff.ui.theme.Purple700

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val menuItems = mapOf(
            getString(R.string.locations) to {
                startActivity(Intent(this, LocationsListActivity::class.java))
            }
        )

        setContent {
            MinecraftyStuffTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    MainGrid(menuItems.keys.toList()) { name ->
                        requireNotNull(menuItems[name])
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainGrid(menuItems: List<String>, getClickListener: (String) -> (() -> Unit)) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        items(menuItems) { name ->
            MenuItem(name = name, getClickListener(name))
        }
    }
}

@Preview
@Composable
fun MainGridPreview() {
    val items = listOf("Item 1", "Item 2", "Item 3")
    MainGrid(menuItems = items) { { } }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MenuItem(name: String, onClick: () -> Unit) {
    Card(
        backgroundColor = Purple200,
        onClick = onClick
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = name,
            color = Purple700
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MenuItemPreview() {
    MenuItem(name = "Item 1") { }
}
