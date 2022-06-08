package com.example.minecraftystuff

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.minecraftystuff.ui.theme.MinecraftyStuffTheme
import com.example.minecraftystuff.ui.theme.Purple200
import com.example.minecraftystuff.ui.theme.Purple700

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MinecraftyStuffApp()
        }
    }
}

@Composable
fun MinecraftyStuffApp() {
    MinecraftyStuffTheme {
        val navController = rememberNavController()

        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        navController.navigate(AppScreen.AddLocation.name)
                    }
                ) {
                    Icon(imageVector = Icons.Filled.Add, stringResource(R.string.add_location))
                }
            },
            isFloatingActionButtonDocked = true,
            bottomBar = {
                BottomAppBar {
                    BottomNavigationItem(
                        icon = { Icon(imageVector = Icons.Filled.Home, stringResource(R.string.home)) },
                        selectedContentColor = Purple700,
                        unselectedContentColor = Purple200,
                        selected = false,
                        onClick = { navController.navigate(AppScreen.MainMenu.name) },
                        label = { Text(stringResource(R.string.home)) }
                    )
                }
            }
        ) { innerPadding ->
            AppNavHost(navController, modifier = Modifier.padding(innerPadding))
        }
    }
}

@Composable
fun AppNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = AppScreen.MainMenu.name,
        modifier = modifier
    ) {
        composable(AppScreen.MainMenu.name) {
            MainGrid(
                menuItems = listOf(
                    MenuItem(
                        name = stringResource(id = R.string.locations),
                        route = AppScreen.Locations.name
                    )
                ),
                onClick = {
                    navController.navigate(it.route)
                }
            )
        }
        composable(AppScreen.Locations.name) {
            LocationList(listOf())
        }
        composable(AppScreen.AddLocation.name) {
            LocationForm()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainGrid(menuItems: List<MenuItem>, onClick: (MenuItem) -> Unit = {}) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        items(menuItems) { menuItem ->
            MenuItemCard(
                item = menuItem,
                onClick = { onClick(menuItem) }
            )
        }
    }
}

data class MenuItem(
    val name: String,
    val route: String
)

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MenuItemCard(item: MenuItem, onClick: () -> Unit) {
    Card(
        backgroundColor = Purple200,
        onClick = onClick
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = item.name,
            color = Purple700
        )
    }
}

// previews
@Preview
@Composable
private fun MainGridPreview() {
    val items = listOf(
        MenuItem("Item 1", ""),
        MenuItem("Item 2", ""),
        MenuItem("Item 3", "")
    )
    MainGrid(menuItems = items) { }
}

@Preview(showBackground = true)
@Composable
private fun MenuItemPreview() {
    MenuItemCard(MenuItem(name = "Item 1", route = "")) { }
}
