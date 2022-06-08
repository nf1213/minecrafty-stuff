package com.example.minecraftystuff

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
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
import com.example.minecraftystuff.AddLocationActivity.Companion.MINECRAFT_HORIZONTAL_WORLD_MAX
import com.example.minecraftystuff.AddLocationActivity.Companion.MINECRAFT_VERTICAL_MAX
import com.example.minecraftystuff.AddLocationActivity.Companion.MINECRAFT_VERTICAL_MIN
import com.example.minecraftystuff.ui.theme.MinecraftyStuffTheme

class AddLocationActivity : ComponentActivity() {

    companion object {
        const val MINECRAFT_HORIZONTAL_WORLD_MAX = 29999872
        const val MINECRAFT_VERTICAL_MIN = -64
        const val MINECRAFT_VERTICAL_MAX = 320
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MinecraftyStuffTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    LocationForm()
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun LocationForm() {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        NumberInputField(label = stringResource(id = R.string.x_coordinate))
        NumberInputField(
            label = stringResource(id = R.string.y_coordinate),
            min = MINECRAFT_VERTICAL_MIN,
            max = MINECRAFT_VERTICAL_MAX
        )
        NumberInputField(label = stringResource(id = R.string.z_coordinate))
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { /* TODO save */ }
        ) {
            Text(text = stringResource(id = R.string.save))
        }
    }
}

@Composable
fun NumberInputField(label: String, min: Int = -MINECRAFT_HORIZONTAL_WORLD_MAX, max: Int = MINECRAFT_HORIZONTAL_WORLD_MAX) {
    // todo validation
    var text by remember { mutableStateOf("") }

    TextField(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        value = text,
        label = { Text(text = label)},
        onValueChange = { text = it },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}





