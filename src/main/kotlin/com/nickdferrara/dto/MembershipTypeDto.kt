package com.nickdferrara.dto

import com.nickdferrara.models.Description
import kotlinx.serialization.Serializable

@Serializable
data class MembershipTypeDto(
    val id: String? = null,
    val description: Description,
    val isReoccuring: Boolean,
    )
