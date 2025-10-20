package com.sunstrinq.codebook.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AccountTree
import androidx.compose.material.icons.filled.Animation
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.DataObject
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material.icons.filled.TextFields
import androidx.compose.material.icons.filled.ViewCarousel
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// --- 1. Data Models ---

enum class FeatureCategory {
    UI_COMPONENTS,
    ARCHITECTURE,
    DEVICE_APIS,
    ANIMATION
}

data class FeatureItem(
    val id: String,
    val title: String,
    val description: String,
    val icon: ImageVector,
    val category: FeatureCategory,
    val route: String // For Jetpack Navigation
)

// --- 2. Sample Data ---

val sampleFeatures = listOf(
    FeatureItem(
        "buttons",
        "Buttons",
        "All Material 3 button types",
        Icons.Default.ViewCarousel,
        FeatureCategory.UI_COMPONENTS,
        "feature/buttons"
    ),
    FeatureItem(
        "textfields",
        "Text Fields",
        "Inputs, validation, and styles",
        Icons.Default.TextFields,
        FeatureCategory.UI_COMPONENTS,
        "feature/textfields"
    ),
    FeatureItem(
        "mvvm",
        "MVVM",
        "State management with ViewModels",
        Icons.Default.AccountTree,
        FeatureCategory.ARCHITECTURE,
        "feature/mvvm"
    ),
    FeatureItem(
        "room",
        "Room DB",
        "Local persistence with Room",
        Icons.Default.Storage,
        FeatureCategory.ARCHITECTURE,
        "feature/room"
    ),
    FeatureItem(
        "hilt",
        "Hilt",
        "Dependency Injection",
        Icons.Default.Build,
        FeatureCategory.ARCHITECTURE,
        "feature/hilt"
    ),
    FeatureItem(
        "retrofit",
        "Retrofit",
        "Networking with type-safety",
        Icons.Default.Cloud,
        FeatureCategory.ARCHITECTURE,
        "feature/retrofit"
    ),
    FeatureItem(
        "camerax",
        "CameraX",
        "Photo and video capture",
        Icons.Default.PhoneAndroid,
        FeatureCategory.DEVICE_APIS,
        "feature/camerax"
    ),
    FeatureItem(
        "valueanim",
        "Value Animations",
        "Animate any value smoothly",
        Icons.Default.Animation,
        FeatureCategory.ANIMATION,
        "feature/valueanim"
    ),
    FeatureItem(
        "gestures",
        "Gestures",
        "Drag, tap, and swipe modifiers",
        Icons.Default.DataObject,
        FeatureCategory.ANIMATION,
        "feature/gestures"
    ),
)

// --- 3. Main Home Screen Content ---

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    onFeatureClick: (FeatureItem) -> Unit,
    onGitHubClick: () -> Unit
) {
    // Group the features by their category
    val groupedFeatures = sampleFeatures.groupBy { it.category }

    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        // --- Hero Card ---
        item {
            WelcomeHeroCard(
                onGitHubClick = onGitHubClick,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        // --- Feature Sections ---

        // UI Components Section
        groupedFeatures[FeatureCategory.UI_COMPONENTS]?.let { features ->
            item {
                FeatureSection(
                    title = "UI Components",
                    features = features,
                    onFeatureClick = onFeatureClick
                )
            }
        }

        // Architecture Section
        groupedFeatures[FeatureCategory.ARCHITECTURE]?.let { features ->
            item {
                FeatureSection(
                    title = "Architecture & Libraries",
                    features = features,
                    onFeatureClick = onFeatureClick
                )
            }
        }

        // Device APIs Section
        groupedFeatures[FeatureCategory.DEVICE_APIS]?.let { features ->
            item {
                FeatureSection(
                    title = "Device & Hardware APIs",
                    features = features,
                    onFeatureClick = onFeatureClick
                )
            }
        }

        // Animation Section
        groupedFeatures[FeatureCategory.ANIMATION]?.let { features ->
            item {
                FeatureSection(
                    title = "Animations",
                    features = features,
                    onFeatureClick = onFeatureClick
                )
            }
        }
    }
}

// --- 4. Reusable Section Composable ---

@Composable
fun FeatureSection(
    title: String,
    features: List<FeatureItem>,
    onFeatureClick: (FeatureItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Spacer(Modifier.height(16.dp))

        // Section Header
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(Modifier.height(8.dp))

        // Horizontal Carousel
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(features) { feature ->
                FeatureItemCard(
                    icon = feature.icon,
                    title = feature.title,
                    onClick = { onFeatureClick(feature) }
                )
            }
        }
    }
}

// --- 5. Reusable Feature Item Card ---

@Composable
fun FeatureItemCard(
    icon: ImageVector,
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .size(width = 140.dp, height = 120.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier.size(40.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.height(12.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                maxLines = 2
            )
        }
    }
}

// --- 6. Reusable Hero Card ---

@Composable
fun WelcomeHeroCard(
    onGitHubClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = "Welcome!",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "This app is my personal catalog and standard for building modern Android applications with Jetpack Compose.",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(Modifier.height(16.dp))
            Button(
                onClick = onGitHubClick,
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("View on GitHub")
                Spacer(Modifier.width(8.dp))
                Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "View on GitHub")
            }
        }
    }
}

// --- 7. Preview ---

@Preview(showBackground = true)
@Composable
fun HomeScreenContentPreview() {
    // You would wrap this in your app's theme
    // MaterialTheme {
    HomeScreenContent(
        onFeatureClick = {},
        onGitHubClick = {}
    )
    // }
}