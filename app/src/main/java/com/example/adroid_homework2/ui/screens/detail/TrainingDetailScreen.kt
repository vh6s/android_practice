package com.example.adroid_homework2.ui.screens.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.adroid_homework2.database.toCzechName
import com.example.adroid_homework2.navigation.INavigationRouter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrainingDetailScreen(
    navigationRouter: INavigationRouter,
    viewModel: TrainingDetailViewModel = hiltViewModel(),
    id: Long?
) {
    val state = viewModel.trainingDetailUIState.collectAsStateWithLifecycle()

    LaunchedEffect(id) {
        viewModel.getTrainingById(id)
    }

    val training = state.value.training

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Detail")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navigationRouter.returnBack()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
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
                    IconButton(onClick = {
                        navigationRouter.navigateToAddEditTraining(id)
                    }) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = null
                        )
                    }
                    IconButton(onClick = {
                        viewModel.onDeleteClick()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = null,
                            tint = Color.Red
                        )
                    }
                }
            )
        }
    ) {
            paddingValues ->

        if (state.value.showDeleteDialog) {
            AlertDialog(
                onDismissRequest = { viewModel.onDeleteDismiss() },
                title = { Text("Smazat trénink") },
                text = { Text("Opravdu chcete tento trénink smazat?") },
                confirmButton = {
                    TextButton(onClick = {
                        viewModel.onDeleteConfirm()
                        navigationRouter.returnBack()
                    }) {
                            Text("Smazat", color = Color.Red)
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        viewModel.onDeleteDismiss() }) {
                            Text("Zrušit")
                    }
                }
            )
        }

        Box(modifier = Modifier.padding(paddingValues)) {
            if (state.value.isLoading) {
                Text("Loading...")
            } else {
                training?.let {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(text = training.title, fontSize = 48.sp)
                        Text(text = training.place, fontSize = 24.sp)
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(text = "Délka tréninku trvala ${training.trainingLength} minut")
                        Text(text = "Kalorií spáleno: ${training.burnedCalories}")
                        Text(text = "Aktivita: ${training.activityType.toCzechName()}")
                        Spacer(modifier = Modifier.height(24.dp))
                        Text(text = training.note?: "")
                    }
                }
            }
        }
    }
}
