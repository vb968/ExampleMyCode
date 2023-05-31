package com.b2b.rqst.model

data class Request(
    val price: String,
    val request_number: String,
    val status: String,
    val forms: ArrayList<Form>,
    )
