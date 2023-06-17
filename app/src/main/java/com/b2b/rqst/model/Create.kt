package com.b2b.rqst.model

data class Create(
    val fields: HashMap<String, Any>,
    val uid: String,
    val form_id: Int,
    )
