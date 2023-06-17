package com.b2b.rqst.model

data class ChatSendAnswer(
    val id: Int,
    val message: String,
    val created_at: String,
    val viewed_at: String?,
    val im: Boolean,
    val member: Member?,
    val attachment: Attachment?,
    ){

    data class Member(val name: String)
}
