package com.b2b.rqst.model

data class Element(
    val min_length: Int?,
    val max_length: Int?,
    val max_file_size: Int?,
    val pattern: String?,
    val value_type: String?,
    val placeholder: String?,

    val variants: ArrayList<String>?,


    val min_count: Int?,
    val max_count: Int?,
    )
