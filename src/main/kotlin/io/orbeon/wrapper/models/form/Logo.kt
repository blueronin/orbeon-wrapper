package io.orbeon.wrapper.models.form

import io.orbeon.wrapper.models.BaseCompanion


data class Logo(
    val mediaType: String? = null,
    val filename: String? = null,
    val size: String? = null,
) {
    companion object: BaseCompanion<Logo>() {
        override fun fromJSON(data: Map<String, Any>): Logo {
            return Logo(
                mediaType = data["@mediatype"] as? String,
                filename = data["@filename"] as? String,
                size = data["@size"] as? String,
            )
        }

        override fun fromArray(data: ArrayList<LinkedHashMap<String, String>>): ArrayList<Logo> {
            return arrayListOf(*data.map { this.fromJSON(it) }.toTypedArray())
        }
    }
}
