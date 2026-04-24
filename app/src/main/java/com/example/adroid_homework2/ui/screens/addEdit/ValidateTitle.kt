package com.example.adroid_homework2.ui.screens.addEdit

import com.example.adroid_homework2.R

class ValidateTitle {
    fun execute(title: String): ValidationResult {
        if (title.isBlank()) {
            return ValidationResult(false, R.string.cannot_be_empty)
        }
        if (title.length > 50) {
            return ValidationResult(true,R.string.title_too_long)
        }
        return ValidationResult(true)
    }
}