package com.sunstrinq.codebook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sunstrinq.codebook.ui.screen.HomeScreenContent
import com.sunstrinq.codebook.ui.theme.SunstrinqCodebookTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SunstrinqCodebookTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("My Android Catalog") },
                            actions = {
                                IconButton(onClick = { /* navigate to search */ }) {
                                    Icon(Icons.Default.Search, contentDescription = "Search")
                                }
                                IconButton(onClick = { /* navigate to about me */ }) {
                                    Icon(Icons.Default.Person, contentDescription = "About Me")
                                }
                            }
                        )
                    },
                    bottomBar = {
                        NavigationBar {
                            // NavigationItem for "Home"
                            // NavigationItem for "Search"
                            // NavigationItem for "Favorites"
                        }
                    }
                ) { paddingValues ->
                    HomeScreenContent(
                        modifier = Modifier.padding(paddingValues),
                        onFeatureClick = {},
                        onGitHubClick = {}
                    )
                }
            }
        }
    }
}

@Composable
@Preview
fun MainPreview() {
    SunstrinqCodebookTheme {
        Scaffold { paddingValues ->
            HomeScreenContent(
                modifier = Modifier.padding(paddingValues),
                onFeatureClick = {},
                onGitHubClick = {}
            )
        }
    }
}
