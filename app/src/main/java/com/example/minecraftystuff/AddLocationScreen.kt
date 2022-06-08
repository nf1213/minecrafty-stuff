package com.example.minecraftystuff

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.minecraftystuff.data.Location

const val MINECRAFT_HORIZONTAL_WORLD_MAX = 29999872
const val MINECRAFT_VERTICAL_MIN = -64
const val MINECRAFT_VERTICAL_MAX = 320

@Composable
@Preview(showBackground = true)
fun LocationForm(
    viewModel: LocationsViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = LocationsViewModelFactory(MinecraftyStuffApplication.instance.repository)),
    onSave: () -> Unit = {}
) {
    val state by remember { mutableStateOf(Location()) }

    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        NumberInputField(
            value = state.xCoordinate,
            label = stringResource(id = R.string.x_coordinate),
            onValueChange = { state.xCoordinate = it.toIntOrNull() ?: 0 }
        )
        NumberInputField(
            value = state.xCoordinate,
            label = stringResource(id = R.string.y_coordinate),
            min = MINECRAFT_VERTICAL_MIN,
            max = MINECRAFT_VERTICAL_MAX,
            onValueChange = { state.yCoordinate = it.toIntOrNull() ?: 0 }
        )
        NumberInputField(
            value = state.xCoordinate,
            label = stringResource(id = R.string.z_coordinate),
            onValueChange = { state.zCoordinate = it.toIntOrNull() ?: 0 }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                viewModel.insert(Location(
                    xCoordinate = state.xCoordinate,
                    yCoordinate = state.yCoordinate,
                    zCoordinate = state.zCoordinate
                ))
                onSave()
            }
        ) {
            Text(text = stringResource(id = R.string.save))
        }
    }
}

@Composable
fun NumberInputField(value: Int, label: String, min: Int = -MINECRAFT_HORIZONTAL_WORLD_MAX, max: Int = MINECRAFT_HORIZONTAL_WORLD_MAX, onValueChange: (String) -> Unit) {
    // todo validation
    var state by remember { mutableStateOf(value.toString()) }
    TextField(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        value = state,
        label = { Text(text = label) },
        onValueChange = {
            state = it
            onValueChange(it)
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}





