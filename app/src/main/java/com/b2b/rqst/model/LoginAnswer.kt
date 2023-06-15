package com.b2b.rqst.model

data class LoginAnswer(
    var success: Boolean = false,
    val data: Data?,
    val message: String?,
    val errors: Any?,
    ){

    data class Data(val token: String)
}
