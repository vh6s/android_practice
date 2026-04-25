package com.example.adroid_homework2.ui.screens.addEdit

import com.example.adroid_homework2.database.ActivityType

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
    val note: FormField<String> = FormField(""),

    // pomocna promenna, pro lepsi UX, aby se errory ukazaly az pri pokusu o ulozeni
    val showErrors: Boolean = false,
    val trainingSaved: Boolean = false,
)
