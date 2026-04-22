package com.example.priprava.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.priprava.navigation.INavigationRouter
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditEntityScreen(
    navigationRouter: INavigationRouter,
    viewModel: AddEditEntityViewModel = hiltViewModel(),
    id: Long?
) {
    val state = viewModel.addEditEntityUIState.collectAsStateWithLifecycle()

    if (state.value.entitySaved){
        LaunchedEffect(state.value) {
            navigationRouter.returnBack()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text("Pridat/odebrat")
            },
                navigationIcon = {
                    IconButton(onClick = {
                        navigationRouter.returnBack()
                    }) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                    }
                })
        }
    ) { paddingValues ->
        AddEditEntityScreenContent(
            paddingValues = paddingValues,
            state = state.value,
            actions = viewModel
        )
    }
}

@Composable
fun AddEditEntityScreenContent(
    paddingValues: PaddingValues,
    state: AddEditEntityUIState,
    actions: AddEditEntityScreenActions
) {
    Column(
        modifier = Modifier.padding(paddingValues)
    ) {

        OutlinedTextField(
            value = state.entity.title,
            onValueChange = {
                actions.onTitleChange(it)
            },
            placeholder = {
                Text("Title")
            },
            isError = state.entityTextError != null,
            supportingText = {
                if (state.entityTextError != null) {
                    Text(text = stringResource(state.entityTextError!!))
                }
            }
        )

        OutlinedTextField(
            value = state.entity.text,
            onValueChange = {
                actions.onTextChanged(it)
            },
            placeholder = {
                Text("Text")
            },
            isError = state.entityTextError != null,
            supportingText = {
                if (state.entityTextError != null) {
                    Text(text = stringResource(id = state.entityTextError!!))
                }
            }
        )

        Button(
            onClick = {
                actions.saveEntity()
            }
        ) {
            Text("Ulozit")
        }
    }

}