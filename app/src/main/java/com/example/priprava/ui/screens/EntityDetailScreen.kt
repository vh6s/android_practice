package com.example.priprava.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.priprava.navigation.INavigationRouter

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun EntityDetailScreen(
    navigationRouter: INavigationRouter,
    viewModel: EntityDetailViewModel = hiltViewModel(),
    id: Long?
) {
    val state = viewModel.entityDetailUIState.collectAsStateWithLifecycle()

    LaunchedEffect(id) {
        viewModel.getEntityById(id)
    }
    val entity = state.value.entity

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Movie Detail")
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
                }
            )
        }
    ) {
        paddingValues ->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(bottom = paddingValues.calculateBottomPadding()),
            contentAlignment = Alignment.Center) {
            if (state.value.isLoading) {
                Text("Loading...")
            } else {
                entity?.let {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(text = entity.title, fontSize = 24.sp)
                        Text(text = entity.text, fontSize = 16.sp)
                    }
                }
            }
        }
    }
}