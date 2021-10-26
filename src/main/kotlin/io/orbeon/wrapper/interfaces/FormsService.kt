package io.orbeon.wrapper.interfaces

import io.orbeon.wrapper.models.form.Form
import org.springframework.http.ResponseEntity

interface FormsService {
    fun groupFormsOnApplication(forms: ArrayList<Form>): HashMap<String, ArrayList<Form>>
    fun fetchAll(): ResponseEntity<LinkedHashMap<String, LinkedHashMap<String, Any>>>
    fun combineFormsOnUniqueCanonicalName(forms: ArrayList<Form>): ArrayList<Form>
}