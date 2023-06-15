package com.b2b.rqst.model

data class LoginAnswer(
    val success: Boolean,
    val data: Data,
    ){

    data class Data(val token: String)
}
