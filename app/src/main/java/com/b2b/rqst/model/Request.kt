package com.b2b.rqst.model

import android.content.Context

data class Request(
    val price: String,
    val request_number: String,
    val status: String,
    val forms: ArrayList<Form>,
    ){
    companion object {
        val form = Form("", "label_1", "value_1")
        val forms = ArrayList<Form>()
        fun getTestRequests(): ArrayList<Request>{
            forms.clear()
            repeat(4){
                forms.add(form)
            }
            forms.add(Form(null, "label_1", "value_1"))
            forms.add(Form(null, "label_1", "value_1"))
            val requests = ArrayList<Request>()
            requests.add(Request("$47", "№F15307", "Active", forms))
            requests.add(Request("$48", "№F15308", "Listed", forms))
            requests.add(Request("$48", "№F15308", "Listed", forms))
            requests.add(Request("$48", "№F15308", "Listed", forms))
            return requests
        }

    }
}

