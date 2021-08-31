package io.orbeon.wrapper.models.form

import io.orbeon.wrapper.models.BaseCompanion


data class FrParam(
    val fr: String? = null,
    val type: String? = null,
    val name: String? = null,
    val controlName: String? = null,
) {
    companion object : BaseCompanion<FrParam>() {
        override fun fromJSON(data: Map<String, Any>): FrParam {
            return FrParam(
                fr = data["@xmlns:fr"] as? String,
                type = data["@type"] as? String,
                name = data["fr:name"] as? String,
                controlName = data["fr:controlName"] as? String,
            )
        }

        override fun fromArray(data: ArrayList<LinkedHashMap<String, String>>): ArrayList<FrParam> {
            return arrayListOf(*data.map { this.fromJSON(it) }.toTypedArray())
        }
    }
}
