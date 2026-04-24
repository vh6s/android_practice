package com.example.adroid_homework2.ui.screens.addEdit

import com.example.adroid_homework2.database.ActivityType
import com.example.adroid_homework2.database.TrainingData

//Preddefinuju si datovou strukturu, ktera vyuzije generiku pro jednotlive pole,
//kazde pole tedy drzi svoji hodnotu a vlastni error
data class FormField<T>(
    val value: T,
    val error: Int? = null
)

// Ciselne hodnoty ukladam jako string pro lepsi moznost validace
data class TrainingAddEditUIState(
    val title: FormField<String> = FormField(""),
    val place: FormField<String> = FormField(""),
    val trainingLength: FormField<String> = FormField(""),
    val burnedCalories: FormField<String> = FormField(""),
    val activityType: FormField<ActivityType> = FormField(ActivityType.RUN),
    val note: FormField<String?> = FormField(null),

    val trainingSaved: Boolean = false,
)
