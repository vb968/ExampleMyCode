package com.b2b.rqst.model

data class Create(
    val form_id: Int,
    val name: String?,
    val price: String?,
    val fields: ArrayList<Field>?,
    )
