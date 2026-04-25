package com.example.adroid_homework2.ui.screens.addEdit

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.adroid_homework2.database.ActivityType
import com.example.adroid_homework2.database.toCzechName
import com.example.adroid_homework2.navigation.INavigationRouter
import com.example.adroid_homework2.navigation.ScreenDestination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrainingAddEditScreen(
    navigationRouter: INavigationRouter,
    viewModel: TrainingAddEditViewmodel = hiltViewModel(),
    id: Long?
) {
    val state = viewModel.trainingAddEditUIState.collectAsStateWithLifecycle()

    if (state.value.trainingSaved) {
        LaunchedEffect(state.value) {
            navigationRouter.returnBack()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text("Záznam")
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
        AddEditTrainingScreenContent(
            paddingValues = paddingValues,
            state = state.value,
            actions = viewModel,
            viewModel = viewModel,
            id = id
        )
    }
}

@Composable
fun AddEditTrainingScreenContent(
    paddingValues: PaddingValues,
    state: TrainingAddEditUIState,
    actions: TrainingAddEditScreenActions,
    viewModel: TrainingAddEditViewmodel,
    id: Long?
) {
    LaunchedEffect(id) {
        viewModel.getTrainingById(id)
    }

    Column(
        modifier = Modifier.padding(paddingValues)
    ) {
        //nazev
        OutlinedTextField(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            value = state.title.value,
            onValueChange = { value ->
                actions.onTitleChange(value)
            },
            label = {
                Text("Název")
            },
            isError = state.showErrors && state.title.error != null,
            supportingText = {
                if (state.showErrors) {
                    state.title.error?.let {
                        Text(text = stringResource(it))
                    }
                }
            }
        )

        // misto
        OutlinedTextField(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            value = state.place.value,
            onValueChange = { value ->
                actions.onPlaceChange(value)
            },
            label = {
                Text("Místo")
            },
            isError = state.showErrors && state.place.error != null,
            supportingText = {
                if (state.showErrors) {
                    state.place.error?.let {
                        Text(text = stringResource(it))
                    }
                }
            }
        )

        // training delka
        OutlinedTextField(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            value = state.trainingLength.value,
            onValueChange = { value ->
                actions.onTrainingLengthChange(value)
            },
            label = {
                Text("Délka tréninku")
            },
            isError = state.showErrors && state.trainingLength.error != null,
            supportingText = {
                if (state.showErrors) {
                    state.trainingLength.error?.let {
                        Text(text = stringResource(it))
                    }
                }
            }
        )

        // kalorie
        OutlinedTextField(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            value = state.burnedCalories.value,
            onValueChange = { value ->
                actions.onBurnedCaloriesChange(value)
            },
            label = {
                Text("Spálené kalorie")
            },
            isError = state.showErrors && state.burnedCalories.error != null,
            supportingText = {
                if (state.showErrors) {
                    state.burnedCalories.error?.let {
                        Text(text = stringResource(it))
                    }
                }
            }
        )

        ActivityTypeDropdown(
            state = state.activityType,
            showErrors = state.showErrors,
            // zkraceny zapis pres odkaz na funkci (function reference)
            onValueChange = actions::onActivityTypeChange,
        )

        // poznamka
        OutlinedTextField(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            value = state.note.value,
            onValueChange = { value ->
                actions.onNoteChange(value)
            },
            label = {
                Text("Poznámka")
            },
            maxLines = 6,
            isError = state.showErrors && state.note.error != null,
            supportingText = {
                if (state.showErrors) {
                    state.note.error?.let {
                        Text(text = stringResource(it))
                    }
                }
            }
        )

        Button(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            onClick = {
                actions.saveTraining()
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF317C34))
        ) {
            Text("Uložit")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActivityTypeDropdown(
    state: FormField<ActivityType>,
    showErrors: Boolean,
    onValueChange: (ActivityType) -> Unit
) {
    //sprava lokalniho stavu, u rozbalovaciho menu sleduje jestli je zavrene / otevrene
    var expanded by remember { mutableStateOf(false) }


    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {

        OutlinedTextField(
            modifier = Modifier
                .menuAnchor(type = MenuAnchorType.PrimaryNotEditable)
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            value = state.value.toCzechName(),
            onValueChange = {},
            readOnly = true,
            label = { Text("Aktivita") },
            supportingText = {},
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            isError = showErrors && state.error != null
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            ActivityType.entries.forEach { activity ->
                DropdownMenuItem(
                    text = { Text(activity.toCzechName()) },
                    onClick = {
                        onValueChange(activity)
                        expanded = false
                    }
                )
            }
        }
    }
}