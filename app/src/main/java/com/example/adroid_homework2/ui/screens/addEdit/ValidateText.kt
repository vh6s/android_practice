package com.example.adroid_homework2.ui.screens.addEdit

import com.example.adroid_homework2.R

class ValidateText {
    fun execute(text: String): ValidationResult {
        if (text.isBlank()) {
            return ValidationResult(false, R.string.cannot_be_empty)
        }
        if (text.length > 50) {
            return ValidationResult(true,R.string.text_too_long)
        }
        return ValidationResult(true)
    }
}