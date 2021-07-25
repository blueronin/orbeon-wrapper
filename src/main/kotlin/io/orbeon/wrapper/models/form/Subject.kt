package io.orbeon.wrapper.models.form


data class Subject(
    val template: ArrayList<TranslateText>? = arrayListOf(),
    val frParam: ArrayList<FrParam>? = arrayListOf(),
) {
    companion object : Base<Subject>() {
        override fun fromJSON(data: Map<String, Any>?): Subject {
            return Subject(
                template = TranslateText.fromMixedJSON(data?.get("template")),
                frParam = FrParam.fromMixedJSON(data?.get("fr:param"))
            )
        }

        override fun fromArray(data: ArrayList<LinkedHashMap<String, String>>?): ArrayList<Subject> {
            if (data == null) {
                return arrayListOf()
            }
            return arrayListOf(*data.map { this.fromJSON(it) }.toTypedArray())
        }
    }
}