package com.b2b.rqst.model

data class BaseAnswer<T>(
    val success: Boolean,
    val data: T,
    val message: String?,
    )
