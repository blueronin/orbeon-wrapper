package io.orbeon.wrapper.models.form

import io.orbeon.wrapper.models.BaseCompanion


data class TranslateText(
    val lang: String? = null,
    val text: String? = null,
) {
    companion object : BaseCompanion<TranslateText>() {
        override fun fromJSON(data: Map<String, Any>): TranslateText {
            return TranslateText(
                lang = data["@xml:lang"] as? String,
                text = data["#text"] as? String,
            )
        }

        override fun fromArray(data: ArrayList<LinkedHashMap<String, String>>): ArrayList<TranslateText> {
            return arrayListOf(*data.map { this.fromJSON(it) }.toTypedArray())
        }
    }
}
