package com.example.adroid_homework2.ui.screens.list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.DirectionsRun
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.PedalBike
import androidx.compose.material.icons.filled.Pool
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.adroid_homework2.database.ActivityType
import com.example.adroid_homework2.database.TrainingData
import com.example.adroid_homework2.navigation.INavigationRouter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrainingListScreen(
    navigationRouter: INavigationRouter,
    viewModel: TrainingListViewModel = hiltViewModel()
) {
    val state = viewModel.trainingListUIState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title =  {
                    Text("Seznam tréninků")
                },
                actions = {
                    IconButton(onClick = {
                        navigationRouter.navigateToTrainingStatistics()
                    }) {
                        Icon(
                            imageVector = Icons.Default.BarChart,
                            contentDescription = null
                        )
                    }
                })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navigationRouter.navigateToAddEditTraining(null)
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }

    ) { paddingValues ->
        WordListScreenContent(
            paddingValues = paddingValues,
            navigationRouter = navigationRouter,
            state = state.value
        )

    }
}

@Composable
fun WordListScreenContent(
    paddingValues: PaddingValues,
    navigationRouter: INavigationRouter,
    state: TrainingListUIState
) {
    if (!state.trainings.isNullOrEmpty()) {
        LazyColumn(
            modifier = Modifier.padding(paddingValues)
                .padding(horizontal = 8.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            state.trainings.forEach { training ->
                item {
                    TrainingRow(
                        training = training,
                        onClick = {
                            navigationRouter.navigateToTrainingDetail(training.id)
                        })
                }
            }
        }
    } else {
        // TODO seznam je prazdny
    }
}

@Composable
fun TrainingRow(
    training: TrainingData,
    onClick: () -> Unit
) {
    val color = getColorForItem(training.id)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = {
                onClick()
            }),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.LightGray),
        border = BorderStroke(1.dp, Color.DarkGray)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(color, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = getIconForActivity(training.activityType),
                    contentDescription = null,
                    tint = Color.Black
                )
            }

            Column(
                modifier = Modifier.weight(1f)
                    .padding(horizontal = 12.dp)
            ) {
                Text(
                    text = training.title,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = training.place,
                    style = MaterialTheme.typography.bodyMedium
                )

            }
            Text(
                text = "${training.trainingLength} minut",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Light
            )
        }


    }
}

fun getIconForActivity(activity: ActivityType): ImageVector {
    return when (activity) {
        ActivityType.RUN -> Icons.AutoMirrored.Filled.DirectionsRun
        ActivityType.CYCLE -> Icons.Default.PedalBike
        ActivityType.SWIM -> Icons.Default.Pool
        ActivityType.GYM -> Icons.Default.FitnessCenter
    }
}

fun getColorForItem(id: Long?): Color {
    val trainingColors = listOf(
        Color(0xFFE57373),
        Color(0xFF64B5F6),
        Color(0xFF81C784),
        Color(0xFFFFB74D),
        Color(0xFFBA68C8),
        Color(0xFF4DB6AC),
        Color(0xFFA1887F),
        Color(0xFF90A4AE)
    )
    val colorIndex = ((id ?: 0L) % trainingColors.size).toInt()
    return trainingColors[colorIndex]
}
