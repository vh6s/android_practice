package com.example.priprava.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.priprava.database.Entity
import com.example.priprava.navigation.INavigationRouter
import com.example.priprava.navigation.NavigationRouterImpl

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntityListScreen(
    navigationRouter: INavigationRouter,
    viewModel: EntityListViewModel = hiltViewModel()
) {
    val state = viewModel.entityListUIState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(title =  {
                Text("TEXT V TOP BARU")
            })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navigationRouter.navigateToAddEditEntity(null)
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }

    ) { paddingValues ->
        EntityListScreenContent(
            paddingValues = paddingValues,
            navigationRouter = navigationRouter,
            state = state.value
        )

    }
}

@Composable
fun EntityListScreenContent(
    paddingValues: PaddingValues,
    navigationRouter: INavigationRouter,
    state: EntityListUIState
) {
    if (!state.entities.isNullOrEmpty()) {
        LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {
            state.entities.forEach { entity ->
                item {
                    EntityRow(
                        entity = entity,
                        onClick = {
                            navigationRouter.navigateToEntityDetail(entity.id)
                        })
                }

            }
        }
    } else {
        // TODO seznam je prazdny
    }
}

@Composable
fun EntityRow(
    entity: Entity,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = {
                onClick()
            }).padding(8.dp)
    ) {
        Column() {
            Text(text = entity.title, fontSize = 24.sp)
            Text(text = entity.text, fontSize = 16.sp)
        }

    }
}