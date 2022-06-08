package com.example.minecraftystuff

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.minecraftystuff.data.Location


@Composable
fun LocationsListScreen(viewModel: LocationsViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = LocationsViewModelFactory(MinecraftyStuffApplication.instance.repository))) {
    val locations: List<Location> by viewModel.allLocations.observeAsState(listOf())
    LocationList(locations =  locations)
}

@Composable
fun LocationList(locations: List<Location>) {
    LazyColumn {
        items(locations) { location ->
            LocationItem(location)
        }
    }
}

@Composable
private fun LocationItem(location: Location) {
    Column(
        modifier = Modifier.padding(
            PaddingValues(
                horizontal = 8.dp,
                vertical = 8.dp
        )
    )) {
        Text(text = location.name, fontWeight = FontWeight.Bold)
        Text(text = "${location.xCoordinate}, ${location.yCoordinate}, ${location.zCoordinate}")
        Divider()
    }
}

@Preview(showBackground = true)
@Composable
private fun LocationsListPreview() {
    LocationList(locations = listOf(
        Location(id = 1, xCoordinate = 398, yCoordinate = 69, zCoordinate = 88),
        Location(id = 2, xCoordinate = 103, yCoordinate = 57, zCoordinate = -217),
        Location(id = 3, xCoordinate = -844, yCoordinate = 64, zCoordinate = -12)
    ))
}



