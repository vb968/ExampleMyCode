package com.b2b.rqst.model

data class RequestTest(
    val price: String,
    val request_number: String,
    val status: String,
    val forms: ArrayList<FormTest>,
    ){
    companion object {
        val form = FormTest("", "label_1", "value_1")
        val forms = ArrayList<FormTest>()
        fun getTestRequests(): ArrayList<RequestTest>{
            forms.clear()
            repeat(4){
                forms.add(form)
            }
            forms.add(FormTest(null, "label_1", "value_1"))
            forms.add(FormTest(null, "label_1", "value_1"))
            val requests = ArrayList<RequestTest>()
            requests.add(RequestTest("$47", "№F15307", "Active", forms))
            requests.add(RequestTest("$48", "№F15308", "Listed", forms))
            requests.add(RequestTest("$48", "№F15308", "Listed", forms))
            requests.add(RequestTest("$48", "№F15308", "Listed", forms))
            return requests
        }

    }
}

