package com.example.adroid_homework2.ui.screens.addEdit

import com.example.adroid_homework2.R

class ValidateDouble {
    fun execute(double: String): ValidationResult {
        if (double.isBlank()) {
            return ValidationResult(false, R.string.cannot_be_empty)
        }

        // nahrazeni carky za tecku
        val decimalPointNormalizer = double.replace(",", ".")

        // Pokud se jedna o cislo, tak elvis operator (?:) vykonna levou strtanu. Pokud se
        // o cislo nejedna, tak provede prikaz na prave strane a vrati ValidationResult
        val check = decimalPointNormalizer.toDoubleOrNull() ?: return ValidationResult(
            false,
            R.string.must_be_double
        )

        if (check < 0.0) {
            return ValidationResult(false, R.string.smaller_than_zero)
        }

        return ValidationResult(true)
    }
}