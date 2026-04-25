package com.example.adroid_homework2.ui.screens.addEdit

import com.example.adroid_homework2.R

class ValidateNote {
    fun execute(note: String): ValidationResult {
        // v poho kdyt je prazdne, nemusi se spoustet dalsi logika
        if (note.isBlank()) {
            return ValidationResult(true)
        }

        val lineCount = note.lines().size

        if (note.length > 300) {
            return ValidationResult(false, R.string.too_many_characters)
        }

        if (lineCount > 6) {
            return ValidationResult(false, R.string.too_many_lines)
        }

        return ValidationResult(true)
    }
}