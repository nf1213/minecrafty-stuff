package com.example.minecraftystuff

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LocationList(locations: List<String>) {
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
private fun LocationsListPreview() {
    LocationList(locations = listOf(
        "398, 69, 88",
        "103, 57, -217",
        "-844, 64, -12"
    ))
}



