package com.example.minecraftystuff

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.minecraftystuff.ui.theme.MinecraftyStuffTheme
import com.example.minecraftystuff.ui.theme.Purple200
import com.example.minecraftystuff.ui.theme.Purple700

class LocationsListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MinecraftyStuffTheme {
                Scaffold(
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = { }
                        ) {
                            Icon(imageVector = Icons.Filled.Add, getString(R.string.add_location))
                        }
                    },
                    isFloatingActionButtonDocked = true,
                    bottomBar = {
                        BottomAppBar {
                            BottomNavigationItem(
                                icon = { Icon(imageVector = Icons.Filled.Home, getString(R.string.home)) },
                                selectedContentColor = Purple700,
                                unselectedContentColor = Purple200,
                                selected = false,
                                onClick = { finish() },
                                label = { Text(getString(R.string.home)) }
                            )
                        }
                    }
                ) {
                    // todo location database
                    LocationList(locations = listOf())
                }
            }
        }
    }
}

@Composable
private fun LocationList(locations: List<String>) {
    LazyColumn {
        items(locations) { location ->
            LocationItem(location)
        }
    }
}

@Composable
private fun LocationItem(location: String) {
    Column {
        Text(
            text = location,
            modifier = Modifier.padding(
                PaddingValues(
                    horizontal = 8.dp,
                    vertical = 8.dp
                )
            )
        )
        Divider()
    }
}

@Preview(showBackground = true)
@Composable
fun LocationsListPreview() {
    LocationList(locations = listOf(
        "398, 69, 88",
        "103, 57, -217",
        "-844, 64, -12"
    ))
}



