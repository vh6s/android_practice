package com.example.adroid_homework2.ui.screens.addEdit

import com.example.adroid_homework2.database.ActivityType

interface TrainingAddEditScreenActions {
    fun onTitleChange(title: String)
    fun onPlaceChange(place: String)
    fun onTrainingLengthChange(length: Int)
    fun onBurnedCaloriesChange(burnedCalories: Double)
    fun onActivityTypeChange(activity: ActivityType)
    fun onNoteChange(note: String)
    fun saveTraining()
}