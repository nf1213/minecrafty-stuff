package com.example.minecraftystuff

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Icon
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.minecraftystuff.data.Location
import com.example.minecraftystuff.ui.theme.Red


@Composable
fun LocationsListScreen(
    viewModel: LocationsViewModel = viewModel(
        factory = LocationsViewModelFactory(
            MinecraftyStuffApplication.instance.repository
        )
    )
) {
    val locations: List<Location> by viewModel.allLocations.observeAsState(listOf())
    LazyColumn {
        items(items = locations, key = { listItem: Location -> listItem.id }) { location ->
            SwipeToDeleteLocation(
                viewModel = viewModel,
                item = location,
                content = {
                    LocationItem(location)
                })
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun SwipeToDeleteLocation(
    content: @Composable RowScope.() -> Unit,
    item: Location,
    viewModel: LocationsViewModel
) {
    val dismissState = rememberDismissState()

    if (dismissState.isDismissed(DismissDirection.EndToStart)) {
        viewModel.deleteLocation(item)
    }

    SwipeToDismiss(
        state = dismissState,
        modifier = Modifier
            .padding(vertical = 1.dp),
        directions = setOf(
            DismissDirection.EndToStart
        ),
        dismissThresholds = {
            FractionalThreshold(0.2f)
        },
        background = {
            val color by animateColorAsState(
                when (dismissState.targetValue) {
                    DismissValue.Default -> Color.White
                    else -> Red
                }
            )
            val alignment = Alignment.CenterEnd
            val icon = Icons.Default.Delete

            Box(
                Modifier
                    .fillMaxSize()
                    .background(color)
                    .padding(horizontal = 20.dp),
                contentAlignment = alignment
            ) {
                Icon(
                    icon,
                    tint = Color.White,
                    contentDescription = "Delete Icon",
                )
            }
        },
        dismissContent = content
    )
}

@Composable
private fun LocationItem(location: Location) {
    Column(
        modifier = Modifier.padding(
            PaddingValues(
                horizontal = 8.dp,
                vertical = 8.dp
            )
        )
    ) {
        Text(text = location.name, fontWeight = FontWeight.Bold)
        Text(text = "${location.xCoordinate}, ${location.yCoordinate}, ${location.zCoordinate}")
        Divider()
    }
}

@Preview(showBackground = true)
@Composable
private fun LocationsListPreview() {
    val items = listOf(
        Location(id = 1, xCoordinate = 398, yCoordinate = 69, zCoordinate = 88),
        Location(id = 2, xCoordinate = 103, yCoordinate = 57, zCoordinate = -217),
        Location(id = 3, xCoordinate = -844, yCoordinate = 64, zCoordinate = -12)
    )
    LazyColumn {
        items(items) { location ->
            LocationItem(location)
        }
    }
}



