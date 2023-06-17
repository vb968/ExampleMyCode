package com.b2b.rqst.model

data class ChatMessagesAnswer(
    val success: Boolean,
    val data: ArrayList<ChatSendAnswer?>?,
    val links: Links?,
    val meta: Meta?,
    val message: String?,
    )
