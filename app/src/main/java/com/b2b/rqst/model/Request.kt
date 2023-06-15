package com.b2b.rqst.model

data class Request(
    val uid: String?,
    val chat: Chat?,
    val status: String?,
    val form: Form?,
    val fields: ArrayList<Field>?,
    ){
    data class Chat(val uid: String?,)
}
