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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.minecraftystuff.ui.theme.MinecraftyStuffTheme
import com.example.minecraftystuff.ui.theme.PurpleDark
import com.example.minecraftystuff.ui.theme.PurpleLight

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
        val navBackStackEntry by navController.currentBackStackEntryAsState()


        Scaffold(
            floatingActionButton = {
                if (navBackStackEntry?.destination?.hierarchy?.any { it.route == AppScreen.Locations.name } == true ) {
                    FloatingActionButton(
                        onClick = {
                            navController.navigate(AppScreen.AddLocation.name)
                        }
                    ) {
                        Icon(imageVector = Icons.Filled.Add, stringResource(AppScreen.AddLocation.label))
                    }
                }
            },
            bottomBar = {
                BottomAppBar {
                    BottomNavigationItem(
                        icon = { Icon(imageVector = Icons.Filled.Home, stringResource(AppScreen.MainMenu.label)) },
                        selectedContentColor = Color.White,
                        unselectedContentColor = PurpleLight,
                        selected = navBackStackEntry?.destination?.hierarchy?.any { it.route == AppScreen.MainMenu.name } == true,
                        onClick = { navController.navigate(AppScreen.MainMenu.name) },
                        label = { Text(stringResource(AppScreen.MainMenu.label)) }
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
                menuItems = listOf(AppScreen.Locations)
            ) {
                navController.navigate(it.name)
            }
        }
        composable(AppScreen.Locations.name) {
            LocationsListScreen()
        }
        composable(AppScreen.AddLocation.name) {
            LocationForm(
                onSave = {
                    navController.navigate(AppScreen.Locations.name)
                }
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainGrid(menuItems: List<AppScreen>, onClick: (AppScreen) -> Unit = {}) {
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

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MenuItemCard(item: AppScreen, onClick: () -> Unit) {
    Card(
        backgroundColor = PurpleLight,
        onClick = onClick
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text = stringResource(id = item.label),
            color = PurpleDark
        )
    }
}

// previews
@Preview
@Composable
private fun MainGridPreview() {
    val items = listOf(
        AppScreen.Locations,
    )
    MainGrid(menuItems = items) { }
}

@Preview(showBackground = true)
@Composable
private fun MenuItemPreview() {
    MenuItemCard(AppScreen.Locations) { }
}
