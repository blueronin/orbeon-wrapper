package io.orbeon.wrapper.models.form

import io.orbeon.wrapper.models.BaseCompanion


data class Subject(
    val template: ArrayList<TranslateText>? = arrayListOf(),
    val frParam: ArrayList<FrParam>? = arrayListOf(),
) {
    companion object : BaseCompanion<Subject>() {
        override fun fromJSON(data: Map<String, Any>): Subject {
            return Subject(
                template = TranslateText.fromMixedJSON(data["template"]),
                frParam = FrParam.fromMixedJSON(data["fr:param"])
            )
        }

        override fun fromArray(data: ArrayList<LinkedHashMap<String, String>>): ArrayList<Subject> {
            return arrayListOf(*data.map { this.fromJSON(it) }.toTypedArray())
        }
    }
}