package com.example.adroid_homework2.navigation

interface INavigationRouter {
    fun navigateToAddEditTraining(id: Long?)
    fun returnBack()
    fun navigateToTrainingDetail(id: Long?)
    fun navigateToTrainingStatistics()
}