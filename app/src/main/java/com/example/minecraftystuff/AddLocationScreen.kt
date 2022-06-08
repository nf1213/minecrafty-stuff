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
    val xCoordinateField = NumberInputField(
        label = stringResource(id = R.string.x_coordinate),
    )
    val yCoordinateField = NumberInputField(
        label = stringResource(id = R.string.y_coordinate),
        min = MINECRAFT_VERTICAL_MIN,
        max = MINECRAFT_VERTICAL_MAX,
    )
    val zCoordinateField = NumberInputField(
        label = stringResource(id = R.string.z_coordinate),
    )
    val numberInputState by remember { mutableStateOf(NumberInputState(
        listOf(
            xCoordinateField,
            yCoordinateField,
            zCoordinateField
        )
    )) }

    var nameState by remember { mutableStateOf("") }


    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        TextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = nameState,
            label = { Text(text = stringResource(id = R.string.name)) },
            onValueChange = {
                nameState = it
            },
        )
        numberInputState.fields.forEach {
            it.Field()
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                if (numberInputState.validate()) {
                    viewModel.insert(
                        Location(
                            name = nameState,
                            xCoordinate = xCoordinateField.state.toInt(),
                            yCoordinate = yCoordinateField.state.toInt(),
                            zCoordinate = zCoordinateField.state.toInt()
                        )
                    )
                    onSave()
                }
            }
        ) {
            Text(text = stringResource(id = R.string.save))
        }
    }
}

class NumberInputField(
    val label: String,
    private val min: Int = -MINECRAFT_HORIZONTAL_WORLD_MAX,
    private val max: Int = MINECRAFT_HORIZONTAL_WORLD_MAX,
) {
    var state by mutableStateOf("")
    var hasError by mutableStateOf(false)

    @Composable
    fun Field() {
        TextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = state,
            isError = hasError,
            label = { Text(text = label) },
            onValueChange = {
                state = it
                hasError = false
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }

    fun validate(): Boolean {
        val intValue = state.toInt()
        if (intValue < min || intValue > max) {
            hasError = true
            return false
        }
        return true
    }
}

class NumberInputState(var fields: List<NumberInputField>) {
    fun validate(): Boolean {
        var valid = true
        for (field in fields) if (!field.validate()) {
            valid = false
            break
        }
        return valid
    }
}





