package com.example.adroid_homework2.ui.screens.addEdit

import com.example.adroid_homework2.R

class ValidateNumber {
    fun execute(number: String): ValidationResult {
        if (number.isBlank()) {
            return ValidationResult(false, R.string.cannot_be_empty)
        }

        // Pokud se jedna o cislo, tak elvis operator (?:) vykonna levou strtanu. Pokud se
        // o cislo nejedna, tak provede prikaz na prave strane a vrati ValidationResult
        val check = number.toIntOrNull()?: return ValidationResult(false, R.string.must_be_whole_number)

        if (check < 0) {
            return ValidationResult(false, R.string.smaller_than_zero)
        }

        return ValidationResult(true)
    }
}