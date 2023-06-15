package com.b2b.rqst.model

data class FormRequest(
    val success: Boolean,
    val data: ArrayList<Request>?,
    val message: String?,
    )
