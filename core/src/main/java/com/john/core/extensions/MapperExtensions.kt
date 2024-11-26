package com.john.core.extensions

fun String?.requireNotEmptyNotNull(exceptionMessage: String): String {
    if (this == null) {
        throw IllegalArgumentException("$exceptionMessage is required")
    }
    if (this.isBlank() || this.isEmpty()) {
        throw IllegalArgumentException("$exceptionMessage cannot be blank or empty")
    }
    return this
}

fun List<String>?.requireNotEmptyNotNull(exceptionMessage: String): List<String> {
    if (this == null) {
        throw IllegalArgumentException("$exceptionMessage is required")
    }
    if (this.isEmpty()) {
        throw IllegalArgumentException("$exceptionMessage cannot be empty")
    }
    return this
}