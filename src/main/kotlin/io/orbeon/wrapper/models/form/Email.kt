package io.orbeon.wrapper.models.form

import io.orbeon.wrapper.models.BaseCompanion


data class Email(
    val subject: Subject? = null,
) {
    @Suppress("UNCHECKED_CAST")
    companion object : BaseCompanion<Email>() {
        override fun fromJSON(data: Map<String, Any>): Email {
            return Email(
                subject = Subject.fromJSON(data["subject"] as Map<String, Any>),
            )
        }

        override fun fromArray(data: ArrayList<LinkedHashMap<String, String>>): ArrayList<Email> {
            return arrayListOf(*data.map { this.fromJSON(it) }.toTypedArray())
        }
    }
}
