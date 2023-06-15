package com.b2b.rqst.model

data class ChatTest(
    val date: String,
    val incoming_text: String,
    val incoming_time: String,
    val outgoing_text: String,
    val outgoing_time: String,

    ){
    companion object {
        fun getChats(): ArrayList<ChatTest>{
            val chats = ArrayList<ChatTest>()
            chats.add(ChatTest("$47", "№F15307", "Active", "Active", "Active"))
            chats.add(ChatTest("$47", "№F15307", "Active", "Active", "Active"))
            chats.add(ChatTest("$47", "№F15307", "Active", "Active", "Active"))
            chats.add(ChatTest("$47", "№F15307", "Active", "Active", "Active"))
            chats.add(ChatTest("$47", "№F15307", "Active", "Active", "Active"))
            return chats
        }

    }
}

