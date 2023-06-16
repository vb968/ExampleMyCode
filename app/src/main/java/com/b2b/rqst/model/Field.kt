package com.b2b.rqst.model

data class Field(
    val uid: String,
    val label: String?,
    val icon: String?,
    val type: String?,
    val element: Element?,
    val value: ArrayList<String?>?,
    )
