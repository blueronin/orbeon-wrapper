package io.orbeon.wrapper.models.form


data class TranslateText(
    val lang: String? = null,
    val text: String? = null,
) {
    companion object : Base<TranslateText>() {
        override fun fromJSON(data: Map<String, Any>?): TranslateText {
            return TranslateText(
                lang = data?.get("@xml:lang") as? String,
                text = data?.get("#text") as? String,
            )
        }

        override fun fromArray(data: ArrayList<LinkedHashMap<String, String>>?): ArrayList<TranslateText> {
            if (data == null) {
                return arrayListOf()
            }
            return arrayListOf(*data.map { this.fromJSON(it) }.toTypedArray())
        }
    }
}
