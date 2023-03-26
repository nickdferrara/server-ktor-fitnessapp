package com.nickdferrara.models

import kotlinx.serialization.Serializable

data class ErrorResponse(val message: String) {
    @Serializable
    companion object {
        val NOT_FOUND_RESPONSE = ErrorResponse("Person was not found")
        val BAD_REQUEST_RESPONSE = ErrorResponse("Invalid request")
    }
}
