package io.orbeon.wrapper.models.form

import io.orbeon.wrapper.models.BaseCompanion


data class MigrationText(
    val version: String? = null,
    val text: String? = null,
) {
    companion object : BaseCompanion<MigrationText>() {
        override fun fromJSON(data: Map<String, Any>): MigrationText {
            return MigrationText(
                version = data["@version"] as? String,
                text = data["#text"] as? String,
            )
        }

        override fun fromArray(data: ArrayList<LinkedHashMap<String, String>>): ArrayList<MigrationText> {
            return arrayListOf(*data.map { this.fromJSON(it) }.toTypedArray())
        }
    }
}
