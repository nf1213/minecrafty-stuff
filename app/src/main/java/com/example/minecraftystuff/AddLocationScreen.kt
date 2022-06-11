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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

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
            value = viewModel.state.name,
            label = { Text(text = stringResource(id = R.string.name)) },
            onValueChange = {
                viewModel.onNameChange(it)
            },
        )
        TextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = viewModel.state.xCoordinate.value,
            isError = viewModel.state.xCoordinate.error,
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
            value = viewModel.state.yCoordinate.value,
            isError = viewModel.state.yCoordinate.error,
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
            value = viewModel.state.zCoordinate.value,
            isError = viewModel.state.zCoordinate.error,
            label = { Text(text = stringResource(id = R.string.z_coordinate)) },
            onValueChange = {
                viewModel.onZChanged(it)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
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





