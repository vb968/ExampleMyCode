package com.b2b.rqst.model

import android.content.Context

data class Chat(
    val date: String,
    val incoming_text: String,
    val incoming_time: String,
    val outgoing_text: String,
    val outgoing_time: String,

    ){
    companion object {
        fun getChats(): ArrayList<Chat>{
            val chats = ArrayList<Chat>()
            chats.add(Chat("$47", "№F15307", "Active", "Active", "Active"))
            chats.add(Chat("$47", "№F15307", "Active", "Active", "Active"))
            chats.add(Chat("$47", "№F15307", "Active", "Active", "Active"))
            chats.add(Chat("$47", "№F15307", "Active", "Active", "Active"))
            chats.add(Chat("$47", "№F15307", "Active", "Active", "Active"))
            return chats
        }

    }
}

