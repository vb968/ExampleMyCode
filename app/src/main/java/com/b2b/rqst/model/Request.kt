package com.b2b.rqst.model

import android.content.Context

data class Request(
    val price: String,
    val request_number: String,
    val status: String,
    val forms: ArrayList<Form>,
    ){
    companion object {
        val form = Form(null, "label_1", "value_1")
        val forms = ArrayList<Form>()
        fun getTestRequests(): ArrayList<Request>{
            repeat(10){
                forms.add(form)
            }
            val requests = ArrayList<Request>()
            requests.add(Request("$48", "№F15307", "Listed", forms))
            return requests
        }

    }
}

