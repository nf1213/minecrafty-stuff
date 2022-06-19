package com.example.minecraftystuff

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.ExposedDropdownMenuDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.minecraftystuff.data.Biome

@OptIn(ExperimentalMaterialApi::class)
@Composable
@Preview(showBackground = true)
fun LocationForm(
    viewModel: AddLocationViewModel = androidx.lifecycle.viewmodel.compose.viewModel(factory = AddLocationViewModelFactory(MinecraftyStuffApplication.instance.repository)),
    onSave: () -> Unit = {}
) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        TextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = viewModel.name,
            label = { Text(text = stringResource(id = R.string.name)) },
            onValueChange = {
                viewModel.onNameChange(it)
            },
        )
        TextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = viewModel.xValue.value,
            isError = viewModel.xValue.error,
            label = { Text(text = stringResource(id = R.string.x_coordinate)) },
            onValueChange = {
                viewModel.onXChanged(it)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        TextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = viewModel.yValue.value,
            isError = viewModel.yValue.error,
            label = { Text(text = stringResource(id = R.string.y_coordinate)) },
            onValueChange = {
                viewModel.onYChanged(it)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        TextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = viewModel.zValue.value,
            isError = viewModel.zValue.error,
            label = { Text(text = stringResource(id = R.string.z_coordinate)) },
            onValueChange = {
                viewModel.onZChanged(it)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        var expanded by remember { mutableStateOf(false) }
        val biomes: List<Biome> by viewModel.allBiomes.observeAsState(listOf())
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                value = viewModel.biome.name,
                label = { Text(text = stringResource(id = R.string.biome)) },
                readOnly = true,
                onValueChange = { },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    )
                },
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {
                biomes.forEach { biome ->
                    DropdownMenuItem(
                        onClick = {
                            viewModel.onBiomeChanged(biome)
                            expanded = false
                        }
                    ) {
                        Text(text = biome.name)
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                viewModel.save(onSuccess = onSave)
            }
        ) {
            Text(text = stringResource(id = R.string.save))
        }
    }
}





